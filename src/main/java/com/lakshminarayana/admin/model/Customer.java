package com.lakshminarayana.admin.model;

public class Customer {
    private String id;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String aadhaarNumber;
    private String aadhaarDocUrl;
    private String drivingLicenseNumber;
    private String drivingLicenseDocUrl;
    private String kycStatus = "PENDING"; // PENDING, VERIFIED, REJECTED
    private String kycRemarks;
    private String createdAt;

    public Customer() {}

    public Customer(String id, String fullName, String email, String password, String phone,
                    String address, String aadhaarNumber, String aadhaarDocUrl,
                    String drivingLicenseNumber, String drivingLicenseDocUrl,
                    String kycStatus, String kycRemarks, String createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.aadhaarNumber = aadhaarNumber;
        this.aadhaarDocUrl = aadhaarDocUrl;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.drivingLicenseDocUrl = drivingLicenseDocUrl;
        this.kycStatus = kycStatus != null ? kycStatus : "PENDING";
        this.kycRemarks = kycRemarks;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAadhaarNumber() { return aadhaarNumber; }
    public void setAadhaarNumber(String aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }

    public String getAadhaarDocUrl() { return aadhaarDocUrl; }
    public void setAadhaarDocUrl(String aadhaarDocUrl) { this.aadhaarDocUrl = aadhaarDocUrl; }

    public String getDrivingLicenseNumber() { return drivingLicenseNumber; }
    public void setDrivingLicenseNumber(String drivingLicenseNumber) { this.drivingLicenseNumber = drivingLicenseNumber; }

    public String getDrivingLicenseDocUrl() { return drivingLicenseDocUrl; }
    public void setDrivingLicenseDocUrl(String drivingLicenseDocUrl) { this.drivingLicenseDocUrl = drivingLicenseDocUrl; }

    public String getKycStatus() { return kycStatus; }
    public void setKycStatus(String kycStatus) { this.kycStatus = kycStatus; }

    public String getKycRemarks() { return kycRemarks; }
    public void setKycRemarks(String kycRemarks) { this.kycRemarks = kycRemarks; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}

