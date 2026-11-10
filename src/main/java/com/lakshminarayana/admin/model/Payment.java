package com.lakshminarayana.admin.model;

public class Payment {
    private String id;
    private String bookingId;
    private String customerName;
    private String customerEmail;
    private double amount;
    private String paymentMethod;
    private String transactionRef;
    private String paymentDate;
    private String status = "SUCCESS";

    public Payment() {}

    public Payment(String id, String bookingId, String customerName, String customerEmail,
                   double amount, String paymentMethod, String transactionRef,
                   String paymentDate, String status) {
        this.id = id;
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionRef = transactionRef;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getTransactionRef() { return transactionRef; }
    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

