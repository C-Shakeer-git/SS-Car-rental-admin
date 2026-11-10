package com.lakshminarayana.admin.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lakshminarayana.admin.model.Booking;
import com.lakshminarayana.admin.model.Car;
import com.lakshminarayana.admin.model.Customer;
import com.lakshminarayana.admin.model.Payment;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DataStoreService {

    private static final Logger log = LoggerFactory.getLogger(DataStoreService.class);

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Autowired
    private GoogleSheetsService googleSheetsService;

    @Autowired
    private DataSeeder dataSeeder;

    @Value("${data.uploads.dir:./uploads}")
    private String uploadsDir;

    private final Map<String, Car> carsMap = new ConcurrentHashMap<>();
    private final Map<String, Customer> customersMap = new ConcurrentHashMap<>();
    private final Map<String, Booking> bookingsMap = new ConcurrentHashMap<>();
    private final List<Payment> paymentsList = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void init() {
        // Ensure uploads directory exists
        new File(uploadsDir).mkdirs();
        // Load hardcoded seed data
        seedAllData();
        log.info("Data initialized from DataSeeder — {} cars, {} customers, {} bookings, {} payments",
                carsMap.size(), customersMap.size(), bookingsMap.size(), paymentsList.size());
    }

    /**
     * Populate all in-memory data stores from the hardcoded DataSeeder.
     */
    private void seedAllData() {
        for (Car c : dataSeeder.getInitialCars()) {
            carsMap.put(c.getId(), c);
        }
        for (Customer c : dataSeeder.getInitialCustomers()) {
            customersMap.put(c.getId(), c);
        }
        for (Booking b : dataSeeder.getInitialBookings()) {
            bookingsMap.put(b.getId(), b);
        }
        paymentsList.addAll(dataSeeder.getInitialPayments());
    }

    // ═══════════════════════════════════════════════════════════
    //  CARS
    // ═══════════════════════════════════════════════════════════

    public List<Car> getAllCars() {
        return new ArrayList<>(carsMap.values());
    }

    public Optional<Car> getCarById(String id) {
        return Optional.ofNullable(carsMap.get(id));
    }

    public synchronized void saveCar(Car car) {
        if (car.getId() == null || car.getId().isBlank()) {
            car.setId("CAR-" + (100 + carsMap.size() + 1));
        }
        carsMap.put(car.getId(), car);

        if (googleSheetsService.isConnected()) {
            List<Object> row = Arrays.asList(
                    car.getId(), car.getName(), car.getBrand(), car.getCategory(),
                    car.getSeaters(), car.getFuelType(), car.getTransmission(),
                    car.getPricePerDay(), car.getImageUrl(), car.getFeaturesAsString(),
                    car.getMileage(), car.getLuggageCapacity(), car.getRegistrationNumber(),
                    car.isAvailable(), car.getRating(), car.getTripsCompleted()
            );
            googleSheetsService.appendRow("Cars!A:P", row);
        }
    }

    public synchronized boolean deleteCar(String id) {
        Car removed = carsMap.remove(id);
        return removed != null;
    }

    // ═══════════════════════════════════════════════════════════
    //  CUSTOMERS
    // ═══════════════════════════════════════════════════════════

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customersMap.values());
    }

    public Optional<Customer> getCustomerById(String id) {
        return Optional.ofNullable(customersMap.get(id));
    }

    public synchronized void updateCustomerKyc(String customerId, String status, String remarks) {
        Customer c = customersMap.get(customerId);
        if (c != null) {
            c.setKycStatus(status);
            c.setKycRemarks(remarks);
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  BOOKINGS
    // ═══════════════════════════════════════════════════════════

    public List<Booking> getAllBookings() {
        return bookingsMap.values().stream()
                .sorted((b1, b2) -> Objects.toString(b2.getCreatedAt(), "").compareTo(Objects.toString(b1.getCreatedAt(), "")))
                .collect(Collectors.toList());
    }

    public Optional<Booking> getBookingById(String id) {
        return Optional.ofNullable(bookingsMap.get(id));
    }

    public List<Booking> filterBookings(String status, String startDate, String endDate) {
        return bookingsMap.values().stream()
                .filter(b -> {
                    if (status != null && !status.isBlank() && !"ALL".equalsIgnoreCase(status)) {
                        if (!status.equalsIgnoreCase(b.getStatus())) return false;
                    }
                    if (startDate != null && !startDate.isBlank()) {
                        try {
                            LocalDate start = LocalDate.parse(startDate);
                            LocalDate bDate = LocalDate.parse(b.getPickupDate());
                            if (bDate.isBefore(start)) return false;
                        } catch (Exception ignored) {}
                    }
                    if (endDate != null && !endDate.isBlank()) {
                        try {
                            LocalDate end = LocalDate.parse(endDate);
                            LocalDate bDate = LocalDate.parse(b.getPickupDate());
                            if (bDate.isAfter(end)) return false;
                        } catch (Exception ignored) {}
                    }
                    return true;
                })
                .sorted((b1, b2) -> Objects.toString(b2.getCreatedAt(), "").compareTo(Objects.toString(b1.getCreatedAt(), "")))
                .collect(Collectors.toList());
    }

    public synchronized void updateBookingStatus(String id, String status) {
        Booking b = bookingsMap.get(id);
        if (b != null) {
            b.setStatus(status);
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  PAYMENTS
    // ═══════════════════════════════════════════════════════════

    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentsList);
    }

    // ═══════════════════════════════════════════════════════════
    //  FULL SYNC WITH GOOGLE SHEETS
    // ═══════════════════════════════════════════════════════════

    public synchronized boolean syncAllToGoogleSheets() {
        if (!googleSheetsService.isConnected()) {
            return false;
        }

        try {
            // 1. Sync Cars
            List<List<Object>> carRows = new ArrayList<>();
            carRows.add(Arrays.asList("ID", "Name", "Brand", "Category", "Seaters", "FuelType", "Transmission", "PricePerDay", "ImageUrl", "Features", "Mileage", "LuggageCapacity", "RegistrationNumber", "Available", "Rating", "TripsCompleted"));
            for (Car c : carsMap.values()) {
                carRows.add(Arrays.asList(c.getId(), c.getName(), c.getBrand(), c.getCategory(), c.getSeaters(), c.getFuelType(), c.getTransmission(), c.getPricePerDay(), c.getImageUrl(), c.getFeaturesAsString(), c.getMileage(), c.getLuggageCapacity(), c.getRegistrationNumber(), c.isAvailable(), c.getRating(), c.getTripsCompleted()));
            }
            googleSheetsService.updateSheetRange("Cars!A1:P" + carRows.size(), carRows);

            // 2. Sync Customers
            List<List<Object>> custRows = new ArrayList<>();
            custRows.add(Arrays.asList("ID", "FullName", "Email", "Password", "Phone", "Address", "AadhaarNumber", "AadhaarDocUrl", "DrivingLicenseNumber", "DrivingLicenseDocUrl", "KYCStatus", "KYCRemarks", "CreatedAt"));
            for (Customer cu : customersMap.values()) {
                custRows.add(Arrays.asList(cu.getId(), cu.getFullName(), cu.getEmail(), cu.getPassword(), cu.getPhone(), cu.getAddress(), cu.getAadhaarNumber(), cu.getAadhaarDocUrl(), cu.getDrivingLicenseNumber(), cu.getDrivingLicenseDocUrl(), cu.getKycStatus(), cu.getKycRemarks(), cu.getCreatedAt()));
            }
            googleSheetsService.updateSheetRange("Customers!A1:M" + custRows.size(), custRows);

            // 3. Sync Bookings
            List<List<Object>> bookRows = new ArrayList<>();
            bookRows.add(Arrays.asList("ID", "CustomerId", "CustomerName", "CustomerEmail", "CarId", "CarName", "PickupDate", "DropDate", "PickupLocation", "DropLocation", "TotalDays", "DailyRate", "SelectedFeatures", "BaseAmount", "AddOnAmount", "GSTAmount", "TotalAmount", "Status", "PaymentStatus", "PaymentMethod", "TransactionId", "CreatedAt"));
            for (Booking bk : bookingsMap.values()) {
                bookRows.add(Arrays.asList(bk.getId(), bk.getCustomerId(), bk.getCustomerName(), bk.getCustomerEmail(), bk.getCarId(), bk.getCarName(), bk.getPickupDate(), bk.getDropDate(), bk.getPickupLocation(), bk.getDropLocation(), bk.getTotalDays(), bk.getDailyRate(), bk.getSelectedFeaturesAsString(), bk.getBaseAmount(), bk.getAddOnAmount(), bk.getGstAmount(), bk.getTotalAmount(), bk.getStatus(), bk.getPaymentStatus(), bk.getPaymentMethod(), bk.getTransactionId(), bk.getCreatedAt()));
            }
            googleSheetsService.updateSheetRange("Bookings!A1:V" + bookRows.size(), bookRows);

            // 4. Sync Payments
            List<List<Object>> payRows = new ArrayList<>();
            payRows.add(Arrays.asList("ID", "BookingId", "CustomerName", "Amount", "PaymentMethod", "TransactionRef", "PaymentDate", "Status"));
            for (Payment p : paymentsList) {
                payRows.add(Arrays.asList(p.getId(), p.getBookingId(), p.getCustomerName(), p.getAmount(), p.getPaymentMethod(), p.getTransactionRef(), p.getPaymentDate(), p.getStatus()));
            }
            googleSheetsService.updateSheetRange("Payments!A1:H" + payRows.size(), payRows);

            return true;
        } catch (Exception e) {
            log.error("Failed sync to Google Sheets: {}", e.getMessage());
            return false;
        }
    }
}
