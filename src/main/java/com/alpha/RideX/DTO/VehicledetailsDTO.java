package com.alpha.RideX.DTO;

import com.alpha.RideX.Entity.Vechile;

public class VehicledetailsDTO {
	private Vechile v;
	private double fare;
	private double estimationTIme;
	
	
	public Vechile getV() {
		return v;
	}
	public void setV(Vechile v) {
		this.v = v;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public double getEstimationTIme() {
		return estimationTIme;
	}
	public void setEstimationTIme(double estimationTIme) {
		this.estimationTIme = estimationTIme;
	}
	@Override
	public String toString() {
		return "Vehicledetails [v=" + v + ", fare=" + fare + ", estimationTIme=" + estimationTIme + "]";
	}
	
}
