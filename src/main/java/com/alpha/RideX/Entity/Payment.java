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
	public Payment() {
		super();
	}
	public Payment(int paymentId, double amount, String paymentType, PaymentStatus paymentStatus,
			LocalDateTime paymentTime, Bookings booking) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.paymentTime = paymentTime;
		this.booking = booking;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}
    
    
}