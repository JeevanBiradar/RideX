package com.alpha.RideX.DTO;

import java.time.LocalDateTime;
import com.alpha.RideX.Entity.BookingStatus;
import com.alpha.RideX.Entity.PaymentStatus;

public class BookingResponseDTO {
    
    private int bookingId;
    private double fare;
    private double distanceKm;
    private String estimatedTime;
    private BookingStatus status;
    private PaymentStatus paymentStatus;
    private String driverName;      
    private String vehicleModel;    
    private LocalDateTime bookingTime;

    
    public BookingResponseDTO(int bookingId, double fare, double distanceKm, String estimatedTime, 
                              BookingStatus status, PaymentStatus paymentStatus, 
                              String driverName, String vehicleModel, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.fare = fare;
        this.distanceKm = distanceKm;
        this.estimatedTime = estimatedTime;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.driverName = driverName;
        this.vehicleModel = vehicleModel;
        this.bookingTime = bookingTime;
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public String getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(String estimatedTime) { this.estimatedTime = estimatedTime; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    
    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
}