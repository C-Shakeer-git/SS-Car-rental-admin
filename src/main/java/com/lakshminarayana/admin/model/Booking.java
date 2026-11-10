package com.lakshminarayana.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {
    private String id;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String carId;
    private String carName;
    private String carImageUrl;
    private String pickupLocation;
    private String dropLocation;
    private String pickupDate;
    private String dropDate;
    private String pickupTime = "09:00";
    private String dropTime = "18:00";
    private int totalDays = 1;
    private double dailyRate;
    private List<String> selectedFeatures = new ArrayList<>();
    private double baseAmount;
    private double addOnAmount;
    private double gstAmount;
    private double totalAmount;
    private String status = "PENDING"; // PENDING, APPROVED, ONGOING, COMPLETED, CANCELLED
    private String paymentStatus = "PENDING"; // PENDING, PAID, REFUNDED
    private String paymentMethod;
    private String transactionId;
    private String invoiceNumber;
    private String createdAt;

    public Booking() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }

    public String getCarName() { return carName; }
    public void setCarName(String carName) { this.carName = carName; }

    public String getCarImageUrl() { return carImageUrl; }
    public void setCarImageUrl(String carImageUrl) { this.carImageUrl = carImageUrl; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropLocation() { return dropLocation; }
    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }

    public String getPickupDate() { return pickupDate; }
    public void setPickupDate(String pickupDate) { this.pickupDate = pickupDate; }

    public String getDropDate() { return dropDate; }
    public void setDropDate(String dropDate) { this.dropDate = dropDate; }

    public String getPickupTime() { return pickupTime; }
    public void setPickupTime(String pickupTime) { this.pickupTime = pickupTime; }

    public String getDropTime() { return dropTime; }
    public void setDropTime(String dropTime) { this.dropTime = dropTime; }

    public int getTotalDays() { return totalDays; }
    public void setTotalDays(int totalDays) { this.totalDays = totalDays; }

    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }

    public List<String> getSelectedFeatures() { return selectedFeatures; }
    public void setSelectedFeatures(List<String> selectedFeatures) {
        this.selectedFeatures = selectedFeatures != null ? selectedFeatures : new ArrayList<>();
    }

    public double getBaseAmount() { return baseAmount; }
    public void setBaseAmount(double baseAmount) { this.baseAmount = baseAmount; }

    public double getAddOnAmount() { return addOnAmount; }
    public void setAddOnAmount(double addOnAmount) { this.addOnAmount = addOnAmount; }

    public double getGstAmount() { return gstAmount; }
    public void setGstAmount(double gstAmount) { this.gstAmount = gstAmount; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @JsonIgnore
    public String getSelectedFeaturesAsString() {
        if (selectedFeatures == null || selectedFeatures.isEmpty()) return "";
        return String.join(" | ", selectedFeatures);
    }

    public void setSelectedFeaturesFromString(String feats) {
        if (feats == null || feats.trim().isEmpty()) {
            this.selectedFeatures = new ArrayList<>();
        } else {
            this.selectedFeatures = Arrays.stream(feats.split("\\|"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
        }
    }
}
