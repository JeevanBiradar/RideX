package com.alpha.RideX.DTO;

import java.util.List;

import com.alpha.RideX.Entity.Customer;

public class AvailableVehiclesDTO {
	private Customer c;
	private double distance;
	private String sourceLocation;
	private String destinationLocation;
	private List<VehicledetailsDTO> availabeVehicles;
	
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double calculatedDistance) {
		this.distance = calculatedDistance;
	}
	public String getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	public String getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	public List<VehicledetailsDTO> getAvailabeVehicles() {
		return availabeVehicles;
	}
	public void setAvailabeVehicles(List<VehicledetailsDTO> availabeVehicles) {
		this.availabeVehicles = availabeVehicles;
	}
	@Override
	public String toString() {
		return "AvailableVehiclesDTO [c=" + c + ", distance=" + distance + ", sourceLocation=" + sourceLocation
				+ ", destinationLocation=" + destinationLocation + ", availabeVehicles=" + availabeVehicles + "]";
	}
	
	
}
