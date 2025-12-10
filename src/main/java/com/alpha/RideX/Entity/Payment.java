package com.alpha.RideX.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private double amount;
    private String paymentType; // CASH, UPI, CARD

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; // PENDING, SUCCESS, FAILED
    
    private LocalDateTime paymentTime;

    // INDUSTRY STANDARD: Single Source of Truth
    // We link ONLY to Booking. 
    @OneToOne 
    @JoinColumn(name = "booking_id")
    private Bookings booking; 

    // Getters and Setters...
    public void setBooking(Bookings booking) { this.booking = booking; }
    public Bookings getBooking() { return booking; }
    // ... generate rest
}