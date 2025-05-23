package com.example.model;

import java.time.LocalDate;

public class Car {
    private String registrationNumber;
    private String engineVin;
    private int bodyTypeId;
    private String brand;
    private int year;
    private double engineVolume;
    private String color;

    // getters and setters
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public String getEngineVin() { return engineVin; }
    public void setEngineVin(String engineVin) { this.engineVin = engineVin; }
    public int getBodyTypeId() { return bodyTypeId; }
    public void setBodyTypeId(int bodyTypeId) { this.bodyTypeId = bodyTypeId; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getEngineVolume() { return engineVolume; }
    public void setEngineVolume(double engineVolume) { this.engineVolume = engineVolume; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    @Override
    public String toString() {
        return String.format("%s, %s, %d [%s]", brand, color, year, registrationNumber);
    }
}