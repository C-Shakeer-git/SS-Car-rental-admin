package com.lakshminarayana.admin.service;

import com.lakshminarayana.admin.model.Booking;
import com.lakshminarayana.admin.model.Car;
import com.lakshminarayana.admin.model.Customer;
import com.lakshminarayana.admin.model.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides hardcoded seed data for the application.
 * This eliminates the need for external JSON files or a shared-data folder.
 */
@Component
public class DataSeeder {

    // ═══════════════════════════════════════════════════════════
    //  CARS — 10 vehicles
    // ═══════════════════════════════════════════════════════════
    public List<Car> getInitialCars() {
        List<Car> cars = new ArrayList<>();

        Car c1 = new Car();
        c1.setId("CAR-101");
        c1.setName("Toyota Innova Crysta");
        c1.setBrand("Toyota");
        c1.setCategory("MUV");
        c1.setSeaters(7);
        c1.setFuelType("Diesel");
        c1.setTransmission("Automatic");
        c1.setPricePerDay(3200.0);
        c1.setImageUrl("https://images.unsplash.com/photo-1549399542-7e3f8b79c341?auto=format&fit=crop&w=800&q=80");
        c1.setFeatures(Arrays.asList("Rear AC Vents", "Touchscreen Infotainment", "Captain Seats", "Cruise Control", "7 Airbags", "Hill Start Assist"));
        c1.setMileage("14.5 km/l");
        c1.setLuggageCapacity(4);
        c1.setRegistrationNumber("TS 09 LN 7788");
        c1.setAvailable(true);
        c1.setRating(4.9);
        c1.setTripsCompleted(184);
        cars.add(c1);

        Car c2 = new Car();
        c2.setId("CAR-102");
        c2.setName("Mahindra Thar 4x4");
        c2.setBrand("Mahindra");
        c2.setCategory("SUV");
        c2.setSeaters(5);
        c2.setFuelType("Diesel");
        c2.setTransmission("Manual");
        c2.setPricePerDay(2800.0);
        c2.setImageUrl("https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?auto=format&fit=crop&w=800&q=80");
        c2.setFeatures(Arrays.asList("4x4 Low/High Range", "Convertible Top Option", "Touchscreen Display", "Roll Cage Protection", "All-Terrain Tyres", "Apple CarPlay"));
        c2.setMileage("13.2 km/l");
        c2.setLuggageCapacity(3);
        c2.setRegistrationNumber("TS 08 TH 4004");
        c2.setAvailable(true);
        c2.setRating(4.8);
        c2.setTripsCompleted(126);
        cars.add(c2);

        Car c3 = new Car();
        c3.setId("CAR-103");
        c3.setName("Maruti Suzuki Ertiga CNG");
        c3.setBrand("Maruti Suzuki");
        c3.setCategory("MUV");
        c3.setSeaters(7);
        c3.setFuelType("CNG");
        c3.setTransmission("Manual");
        c3.setPricePerDay(2100.0);
        c3.setImageUrl("https://images.unsplash.com/photo-1590362891991-f776e747a588?auto=format&fit=crop&w=800&q=80");
        c3.setFeatures(Arrays.asList("Ultra High Mileage CNG", "Dual AC", "Flexible 3-Row Seating", "Keyless Entry", "Power Windows", "Bluetooth Audio"));
        c3.setMileage("26.1 km/kg");
        c3.setLuggageCapacity(3);
        c3.setRegistrationNumber("AP 16 ER 5521");
        c3.setAvailable(true);
        c3.setRating(4.7);
        c3.setTripsCompleted(240);
        cars.add(c3);

        Car c4 = new Car();
        c4.setId("CAR-104");
        c4.setName("Hyundai Creta SX(O)");
        c4.setBrand("Hyundai");
        c4.setCategory("SUV");
        c4.setSeaters(5);
        c4.setFuelType("Petrol");
        c4.setTransmission("Automatic");
        c4.setPricePerDay(2500.0);
        c4.setImageUrl("https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=800&q=80");
        c4.setFeatures(Arrays.asList("Panoramic Sunroof", "Ventilated Front Seats", "Bose Premium Audio", "Wireless Phone Charger", "ADAS Level 2", "360 Camera"));
        c4.setMileage("16.8 km/l");
        c4.setLuggageCapacity(3);
        c4.setRegistrationNumber("TS 10 CR 9901");
        c4.setAvailable(true);
        c4.setRating(4.9);
        c4.setTripsCompleted(195);
        cars.add(c4);

        Car c5 = new Car();
        c5.setId("CAR-105");
        c5.setName("Tata Nexon EV Max");
        c5.setBrand("Tata Motors");
        c5.setCategory("SUV");
        c5.setSeaters(5);
        c5.setFuelType("Electric");
        c5.setTransmission("Automatic");
        c5.setPricePerDay(2600.0);
        c5.setImageUrl("https://images.unsplash.com/photo-1563720223185-11003d516935?auto=format&fit=crop&w=800&q=80");
        c5.setFeatures(Arrays.asList("Zero Emissions", "437 km Certified Range", "Fast Charging Support", "Harmon Kardon Audio", "Electronic Parking Brake", "Regenerative Braking"));
        c5.setMileage("437 km/charge");
        c5.setLuggageCapacity(3);
        c5.setRegistrationNumber("TS 07 EV 3322");
        c5.setAvailable(true);
        c5.setRating(4.8);
        c5.setTripsCompleted(98);
        cars.add(c5);

        Car c6 = new Car();
        c6.setId("CAR-106");
        c6.setName("Toyota Fortuner Legender 4x4");
        c6.setBrand("Toyota");
        c6.setCategory("Luxury SUV");
        c6.setSeaters(7);
        c6.setFuelType("Diesel");
        c6.setTransmission("Automatic");
        c6.setPricePerDay(5200.0);
        c6.setImageUrl("https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?auto=format&fit=crop&w=800&q=80");
        c6.setFeatures(Arrays.asList("VIP Road Presence", "204 PS High Power Engine", "Dual Zone Climate Control", "JBL 11-Speaker System", "Handsfree Tailgate", "Drive Modes (Eco/Normal/Sport)"));
        c6.setMileage("12.0 km/l");
        c6.setLuggageCapacity(5);
        c6.setRegistrationNumber("TS 09 FT 0001");
        c6.setAvailable(true);
        c6.setRating(5.0);
        c6.setTripsCompleted(85);
        cars.add(c6);

        Car c7 = new Car();
        c7.setId("CAR-107");
        c7.setName("Maruti Suzuki Swift Dzire CNG");
        c7.setBrand("Maruti Suzuki");
        c7.setCategory("Sedan");
        c7.setSeaters(5);
        c7.setFuelType("CNG");
        c7.setTransmission("Manual");
        c7.setPricePerDay(1600.0);
        c7.setImageUrl("https://images.unsplash.com/photo-1552519507-da3b142c6e3d?auto=format&fit=crop&w=800&q=80");
        c7.setFeatures(Arrays.asList("Pocket Friendly", "31.1 km/kg CNG Mileage", "Spacious Rear Legroom", "Chilled Air Conditioning", "Rear AC Vents", "ABS with EBD"));
        c7.setMileage("31.1 km/kg");
        c7.setLuggageCapacity(2);
        c7.setRegistrationNumber("AP 29 DZ 6634");
        c7.setAvailable(true);
        c7.setRating(4.6);
        c7.setTripsCompleted(310);
        cars.add(c7);

        Car c8 = new Car();
        c8.setId("CAR-108");
        c8.setName("Honda City 5th Gen ZX");
        c8.setBrand("Honda");
        c8.setCategory("Sedan");
        c8.setSeaters(5);
        c8.setFuelType("Petrol");
        c8.setTransmission("Automatic");
        c8.setPricePerDay(2300.0);
        c8.setImageUrl("https://images.unsplash.com/photo-1580273916550-e323be2ae537?auto=format&fit=crop&w=800&q=80");
        c8.setFeatures(Arrays.asList("i-VTEC Smooth Engine", "One-touch Electric Sunroof", "LaneWatch Camera", "8-inch Touchscreen", "Paddle Shifters", "Full LED Headlamps"));
        c8.setMileage("18.4 km/l");
        c8.setLuggageCapacity(4);
        c8.setRegistrationNumber("TS 09 HC 5050");
        c8.setAvailable(true);
        c8.setRating(4.8);
        c8.setTripsCompleted(152);
        cars.add(c8);

        Car c9 = new Car();
        c9.setId("CAR-109");
        c9.setName("Mahindra XUV700 AX7 Luxury");
        c9.setBrand("Mahindra");
        c9.setCategory("SUV");
        c9.setSeaters(7);
        c9.setFuelType("Diesel");
        c9.setTransmission("Automatic");
        c9.setPricePerDay(3600.0);
        c9.setImageUrl("https://images.unsplash.com/photo-1605559424843-9e4c228bf1c2?auto=format&fit=crop&w=800&q=80");
        c9.setFeatures(Arrays.asList("Dual 10.25-inch Screens", "Sony 3D Surround Sound", "Panoramic Skyroof", "Smart Door Handles", "Blind View Monitor", "Autonomous Emergency Braking"));
        c9.setMileage("14.2 km/l");
        c9.setLuggageCapacity(4);
        c9.setRegistrationNumber("TS 07 XU 7007");
        c9.setAvailable(true);
        c9.setRating(4.9);
        c9.setTripsCompleted(110);
        cars.add(c9);

        Car c10 = new Car();
        c10.setId("CAR-110");
        c10.setName("Tata Safari Dark Edition");
        c10.setBrand("Tata Motors");
        c10.setCategory("SUV");
        c10.setSeaters(7);
        c10.setFuelType("Diesel");
        c10.setTransmission("Automatic");
        c10.setPricePerDay(3500.0);
        c10.setImageUrl("https://images.unsplash.com/photo-1549399542-7e3f8b79c341?auto=format&fit=crop&w=800&q=80");
        c10.setFeatures(Arrays.asList("Ventilated 1st & 2nd Row Seats", "Panoramic Sunroof", "JBL 10-Speaker Audio", "360 Camera", "ADAS"));
        c10.setMileage("15.4 km/l");
        c10.setLuggageCapacity(4);
        c10.setRegistrationNumber("TS 09 SF 9988");
        c10.setAvailable(true);
        c10.setRating(4.8);
        c10.setTripsCompleted(0);
        cars.add(c10);

        return cars;
    }

    // ═══════════════════════════════════════════════════════════
    //  CUSTOMERS — 2 sample customers
    // ═══════════════════════════════════════════════════════════
    public List<Customer> getInitialCustomers() {
        List<Customer> customers = new ArrayList<>();

        Customer cu1 = new Customer();
        cu1.setId("CUST-1001");
        cu1.setFullName("Rahul Varma");
        cu1.setEmail("customer@gmail.com");
        cu1.setPassword("password123");
        cu1.setPhone("+91 98480 22338");
        cu1.setAddress("Flat 402, Sri Sai Nilayam, Madhapur, Hyderabad");
        cu1.setAadhaarNumber("4589 1234 5678");
        cu1.setAadhaarDocUrl("https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?auto=format&fit=crop&w=600&q=80");
        cu1.setDrivingLicenseNumber("TS09 20190045882");
        cu1.setDrivingLicenseDocUrl("https://images.unsplash.com/photo-1554224155-8d04cb21cd6c?auto=format&fit=crop&w=600&q=80");
        cu1.setKycStatus("VERIFIED");
        cu1.setKycRemarks("Documents verified successfully by LNT Staff");
        cu1.setCreatedAt("2026-03-01T10:30:00");
        customers.add(cu1);

        Customer cu2 = new Customer();
        cu2.setId("CUST-1002");
        cu2.setFullName("Ananya Sharma");
        cu2.setEmail("ananya.sharma@example.com");
        cu2.setPassword("password123");
        cu2.setPhone("+91 97001 54321");
        cu2.setAddress("B-12, Green Glen Layout, Bellandur, Bangalore");
        cu2.setAadhaarNumber("7890 2345 6789");
        cu2.setAadhaarDocUrl("https://images.unsplash.com/photo-1589829545856-d10d557cf95f?auto=format&fit=crop&w=600&q=80");
        cu2.setDrivingLicenseNumber("KA03 20210088991");
        cu2.setDrivingLicenseDocUrl("https://images.unsplash.com/photo-1450133064473-71024230f91b?auto=format&fit=crop&w=600&q=80");
        cu2.setKycStatus("PENDING");
        cu2.setKycRemarks("DL submitted, Aadhaar under administrative review");
        cu2.setCreatedAt("2026-03-05T14:15:00");
        customers.add(cu2);

        return customers;
    }

    // ═══════════════════════════════════════════════════════════
    //  BOOKINGS — 4 sample bookings
    // ═══════════════════════════════════════════════════════════
    public List<Booking> getInitialBookings() {
        List<Booking> bookings = new ArrayList<>();

        Booking b1 = new Booking();
        b1.setId("LNT-BK-2026-101");
        b1.setCustomerId("CUST-1001");
        b1.setCustomerName("Rahul Varma");
        b1.setCustomerEmail("customer@gmail.com");
        b1.setCustomerPhone("+91 98480 22338");
        b1.setCarId("CAR-101");
        b1.setCarName("Toyota Innova Crysta");
        b1.setCarImageUrl("https://images.unsplash.com/photo-1549399542-7e3f8b79c341?auto=format&fit=crop&w=800&q=80");
        b1.setPickupLocation("Hyderabad RGIA Airport (HYD)");
        b1.setDropLocation("Vijayawada Bus Station");
        b1.setPickupDate("2026-03-12");
        b1.setDropDate("2026-03-15");
        b1.setPickupTime("09:00");
        b1.setDropTime("18:00");
        b1.setTotalDays(3);
        b1.setDailyRate(3200.0);
        b1.setSelectedFeatures(Arrays.asList("GPS Navigation (+₹200/day)", "Comprehensive Zero-Dep Insurance (+₹499/day)"));
        b1.setBaseAmount(9600.0);
        b1.setAddOnAmount(2097.0);
        b1.setGstAmount(2105.46);
        b1.setTotalAmount(13802.46);
        b1.setStatus("APPROVED");
        b1.setPaymentStatus("PAID");
        b1.setPaymentMethod("UPI (Google Pay)");
        b1.setTransactionId("UPI/308129482910/LNT");
        b1.setInvoiceNumber("INV-LNT-2026-101");
        b1.setCreatedAt("2026-03-08T11:20:00");
        bookings.add(b1);

        Booking b2 = new Booking();
        b2.setId("LNT-BK-2026-102");
        b2.setCustomerId("CUST-1001");
        b2.setCustomerName("Rahul Varma");
        b2.setCustomerEmail("customer@gmail.com");
        b2.setCustomerPhone("+91 98480 22338");
        b2.setCarId("CAR-104");
        b2.setCarName("Hyundai Creta SX(O)");
        b2.setCarImageUrl("https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=800&q=80");
        b2.setPickupLocation("Gachibowli Hub, Hyderabad");
        b2.setDropLocation("Gachibowli Hub, Hyderabad");
        b2.setPickupDate("2026-03-20");
        b2.setDropDate("2026-03-22");
        b2.setPickupTime("08:00");
        b2.setDropTime("20:00");
        b2.setTotalDays(2);
        b2.setDailyRate(2500.0);
        b2.setSelectedFeatures(Arrays.asList("Child Safety Seat (+₹150/day)", "Fastag Highway Pass (+₹500 deposit)"));
        b2.setBaseAmount(5000.0);
        b2.setAddOnAmount(800.0);
        b2.setGstAmount(1044.0);
        b2.setTotalAmount(6844.0);
        b2.setStatus("PENDING");
        b2.setPaymentStatus("PAID");
        b2.setPaymentMethod("Credit Card (HDFC)");
        b2.setTransactionId("TXN_CC_9823481234");
        b2.setInvoiceNumber("INV-LNT-2026-102");
        b2.setCreatedAt("2026-03-09T16:45:00");
        bookings.add(b2);

        Booking b3 = new Booking();
        b3.setId("LNT-BK-2026-103");
        b3.setCustomerId("CUST-1002");
        b3.setCustomerName("Ananya Sharma");
        b3.setCustomerEmail("ananya.sharma@example.com");
        b3.setCustomerPhone("+91 97001 54321");
        b3.setCarId("CAR-102");
        b3.setCarName("Mahindra Thar 4x4");
        b3.setCarImageUrl("https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?auto=format&fit=crop&w=800&q=80");
        b3.setPickupLocation("Bengaluru Airport Terminal 1");
        b3.setDropLocation("Coorg Resort Drop");
        b3.setPickupDate("2026-02-14");
        b3.setDropDate("2026-02-17");
        b3.setPickupTime("06:00");
        b3.setDropTime("21:00");
        b3.setTotalDays(3);
        b3.setDailyRate(2800.0);
        b3.setSelectedFeatures(Arrays.asList("GPS Navigation (+₹200/day)", "Additional Driver (+₹300 flat)"));
        b3.setBaseAmount(8400.0);
        b3.setAddOnAmount(900.0);
        b3.setGstAmount(1674.0);
        b3.setTotalAmount(10974.0);
        b3.setStatus("COMPLETED");
        b3.setPaymentStatus("PAID");
        b3.setPaymentMethod("UPI (PhonePe)");
        b3.setTransactionId("UPI/229019283712/LNT");
        b3.setInvoiceNumber("INV-LNT-2026-089");
        b3.setCreatedAt("2026-02-10T09:15:00");
        bookings.add(b3);

        Booking b4 = new Booking();
        b4.setId("LNT-BK-2026-104");
        b4.setCustomerId("CUST-1001");
        b4.setCustomerName("Rahul Varma");
        b4.setCustomerEmail("customer@gmail.com");
        b4.setCustomerPhone("+91 98480 22338");
        b4.setCarId("CAR-101");
        b4.setCarName("Toyota Innova Crysta");
        b4.setCarImageUrl("https://images.unsplash.com/photo-1549399542-7e3f8b79c341?auto=format&fit=crop&w=800&q=80");
        b4.setPickupLocation("Hyderabad RGIA Airport (HYD) Terminal");
        b4.setDropLocation("Vijayawada Bus Terminal & Railway Hub");
        b4.setPickupDate("2026-04-10");
        b4.setDropDate("2026-04-13");
        b4.setPickupTime("09:00");
        b4.setDropTime("18:00");
        b4.setTotalDays(3);
        b4.setDailyRate(3200.0);
        b4.setSelectedFeatures(new ArrayList<>());
        b4.setBaseAmount(9600.0);
        b4.setAddOnAmount(0.0);
        b4.setGstAmount(1728.0);
        b4.setTotalAmount(11328.0);
        b4.setStatus("APPROVED");
        b4.setPaymentStatus("PAID");
        b4.setPaymentMethod("UPI (Google Pay)");
        b4.setTransactionId("UPI/998877665544/LNT");
        b4.setInvoiceNumber("INV-LNT-BK-2026-104");
        b4.setCreatedAt("2026-11-09T19:43:45");
        bookings.add(b4);

        return bookings;
    }

    // ═══════════════════════════════════════════════════════════
    //  PAYMENTS — 4 sample payments
    // ═══════════════════════════════════════════════════════════
    public List<Payment> getInitialPayments() {
        List<Payment> payments = new ArrayList<>();

        Payment p1 = new Payment();
        p1.setId("PAY-1001");
        p1.setBookingId("LNT-BK-2026-101");
        p1.setCustomerName("Rahul Varma");
        p1.setCustomerEmail("customer@gmail.com");
        p1.setAmount(13802.46);
        p1.setPaymentMethod("UPI (Google Pay)");
        p1.setTransactionRef("UPI/308129482910/LNT");
        p1.setPaymentDate("2026-03-08T11:22:15");
        p1.setStatus("SUCCESS");
        payments.add(p1);

        Payment p2 = new Payment();
        p2.setId("PAY-1002");
        p2.setBookingId("LNT-BK-2026-102");
        p2.setCustomerName("Rahul Varma");
        p2.setCustomerEmail("customer@gmail.com");
        p2.setAmount(6844.0);
        p2.setPaymentMethod("Credit Card (HDFC)");
        p2.setTransactionRef("TXN_CC_9823481234");
        p2.setPaymentDate("2026-03-09T16:48:30");
        p2.setStatus("SUCCESS");
        payments.add(p2);

        Payment p3 = new Payment();
        p3.setId("PAY-1003");
        p3.setBookingId("LNT-BK-2026-103");
        p3.setCustomerName("Ananya Sharma");
        p3.setCustomerEmail("ananya.sharma@example.com");
        p3.setAmount(10974.0);
        p3.setPaymentMethod("UPI (PhonePe)");
        p3.setTransactionRef("UPI/229019283712/LNT");
        p3.setPaymentDate("2026-02-10T09:18:42");
        p3.setStatus("SUCCESS");
        payments.add(p3);

        Payment p4 = new Payment();
        p4.setId("PAY-1004");
        p4.setBookingId("LNT-BK-2026-104");
        p4.setCustomerName("Rahul Varma");
        p4.setCustomerEmail("customer@gmail.com");
        p4.setAmount(11328.0);
        p4.setPaymentMethod("UPI (Google Pay)");
        p4.setTransactionRef("UPI/998877665544/LNT");
        p4.setPaymentDate("2026-11-09T19:43:56");
        p4.setStatus("SUCCESS");
        payments.add(p4);

        return payments;
    }
}

