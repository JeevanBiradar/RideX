package com.alpha.RideX.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Bookings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String sourecLocation;
	private String destinationLocation;
	private double distanceTravelled;
	private double fare;
	private String estimationTravelTime;
	private LocalDateTime bookingDate;
	
	@Enumerated(EnumType.STRING)
    private BookingStatus status;
	
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer cust;	
	
	@ManyToOne
	@JoinColumn(name="driver_id")
	private Driver driver;
	
	@OneToOne(mappedBy = "booking")
	private Payment payment;
	
	public Bookings() {
		super();
	}

	
	public Bookings(int id, String sourecLocation, String destinationLocation, double distanceTravelled, double fare,
			String estimationTravelTime, LocalDateTime bookingDate, BookingStatus status, Customer cust, Driver driver,
			Payment payment) {
		super();
		this.id = id;
		this.sourecLocation = sourecLocation;
		this.destinationLocation = destinationLocation;
		this.distanceTravelled = distanceTravelled;
		this.fare = fare;
		this.estimationTravelTime = estimationTravelTime;
		this.bookingDate = bookingDate;
		this.status = status;
		this.cust = cust;
		this.driver = driver;
		this.payment = payment;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourecLocation() {
		return sourecLocation;
	}

	public void setSourecLocation(String sourecLocation) {
		this.sourecLocation = sourecLocation;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public double getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(double distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getEstimationTravelTime() {
		return estimationTravelTime;
	}

	public void setEstimationTravelTime(String estimationTravelTime) {
		this.estimationTravelTime = estimationTravelTime;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	
	public BookingStatus getStatus() {
		return status;
	}


	public void setStatus(BookingStatus status) {
		this.status = status;
	}


	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	
	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}



	@Override
	public String toString() {
		return "Bookings [id=" + id + ", sourecLocation=" + sourecLocation + ", destinationLocation="
				+ destinationLocation + ", distanceTravelled=" + distanceTravelled + ", fare=" + fare
				+ ", estimationTravelTime=" + estimationTravelTime + ", bookingDate=" + bookingDate + ", cust=" + cust
				+ ", driver=" + driver + "]";
	}
	
	


}

