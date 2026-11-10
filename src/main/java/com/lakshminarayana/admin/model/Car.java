package com.lakshminarayana.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {
    private String id;
    private String name;
    private String brand;
    private String category;
    private int seaters;
    private String fuelType;
    private String transmission;
    private double pricePerDay;
    private String imageUrl;
    private List<String> features = new ArrayList<>();
    private String mileage;
    private int luggageCapacity;
    private String registrationNumber;
    private boolean available = true;
    private double rating = 4.8;
    private int tripsCompleted = 0;

    public Car() {}

    public Car(String id, String name, String brand, String category, int seaters, String fuelType,
               String transmission, double pricePerDay, String imageUrl, List<String> features,
               String mileage, int luggageCapacity, String registrationNumber, boolean available,
               double rating, int tripsCompleted) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.seaters = seaters;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.pricePerDay = pricePerDay;
        this.imageUrl = imageUrl;
        this.features = features != null ? features : new ArrayList<>();
        this.mileage = mileage;
        this.luggageCapacity = luggageCapacity;
        this.registrationNumber = registrationNumber;
        this.available = available;
        this.rating = rating;
        this.tripsCompleted = tripsCompleted;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getSeaters() { return seaters; }
    public void setSeaters(int seaters) { this.seaters = seaters; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public String getTransmission() { return transmission; }
    public void setTransmission(String transmission) { this.transmission = transmission; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }

    public String getMileage() { return mileage; }
    public void setMileage(String mileage) { this.mileage = mileage; }

    public int getLuggageCapacity() { return luggageCapacity; }
    public void setLuggageCapacity(int luggageCapacity) { this.luggageCapacity = luggageCapacity; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getTripsCompleted() { return tripsCompleted; }
    public void setTripsCompleted(int tripsCompleted) { this.tripsCompleted = tripsCompleted; }

    @JsonIgnore
    public String getFeaturesAsString() {
        if (features == null || features.isEmpty()) return "";
        return String.join(", ", features);
    }

    public void setFeaturesFromString(String featStr) {
        if (featStr == null || featStr.trim().isEmpty()) {
            this.features = new ArrayList<>();
        } else {
            this.features = Arrays.stream(featStr.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
        }
    }
}

