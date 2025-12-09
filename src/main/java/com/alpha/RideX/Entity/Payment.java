package com.alpha.RideX.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	 @ManyToOne
	    @JoinColumn(name = "customer_id")
	    private Customer c;

	    @ManyToOne
	    @JoinColumn(name = "vehicle_id")
	    private Vechile v;

	    @ManyToOne
	    @JoinColumn(name = "booking_id")
	    private Bookings b;

	private double amount;
	private String paymentType;
	
	public Payment() {
		super();
	}
	
	public Payment(int paymentId, Customer c, Vechile v, Bookings b, double amount, String paymentType) {
		super();
		this.paymentId = paymentId;
		this.c = c;
		this.v = v;
		this.b = b;
		this.amount = amount;
		this.paymentType = paymentType;
	}
	

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public Vechile getV() {
		return v;
	}
	public void setV(Vechile v) {
		this.v = v;
	}
	public Bookings getB() {
		return b;
	}
	public void setB(Bookings b) {
		this.b = b;
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

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", c=" + c + ", v=" + v + ", b=" + b + ", amount=" + amount
				+ ", paymentType=" + paymentType + "]";
	}

	
	
	
	
}
