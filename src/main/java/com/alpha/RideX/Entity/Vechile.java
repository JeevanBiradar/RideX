package com.alpha.RideX.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Vechile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String vname;
	private String vno;
	private String vtype;
	private String model;
	private int capacity;
	private String currentcity;
	private String availablestatus;
	private double priceperkm;
	private int averagespeed;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Driver driver;
	
	
	
	public Vechile() {
		super();
	}
	
	

	public Vechile(int id, String vname, String vno, String vtype, String model, int capacity, String currentcity,
			String availablestatus, int priceperkm, int averagespeed, Driver driver) {
		super();
		this.id = id;
		this.vname = vname;
		this.vno = vno;
		this.vtype = vtype;
		this.model = model;
		this.capacity = capacity;
		this.currentcity = currentcity;
		this.availablestatus = availablestatus;
		this.priceperkm = priceperkm;
		this.averagespeed = averagespeed;
		this.driver = driver;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVname() {
		return vname;
	}

	

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}
	
	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCurrentcity() {
		return currentcity;
	}

	public void setCurrentcity(String currentcity) {
		this.currentcity = currentcity;
	}

	public String getAvailablestatus() {
		return availablestatus;
	}

	public void setAvailablestatus(String availablestatus) {
		this.availablestatus = availablestatus;
	}

	public double getPriceperkm() {
		return priceperkm;
	}

	public void setPriceperkm(double priceperkm) {
		this.priceperkm = priceperkm;
	}

	public int getAveragespeed() {
		return averagespeed;
	}

	public void setAveragespeed(int averagespeed) {
		this.averagespeed = averagespeed;
	}
	
	public Driver getDriver() {
		return driver;
	}



	public void setDriver(Driver driver) {
		this.driver = driver;
	}



	@Override
	public String toString() {
		return "Vechile [id=" + id + ", vname=" + vname + ", vno=" + vno + ", vtype=" + vtype + ", model=" + model
				+ ", capacity=" + capacity + ", currentcity=" + currentcity + ", availablestatus=" + availablestatus
				+ ", priceperkm=" + priceperkm + ", averagespeed=" + averagespeed + "]";
	}



	
	
	
	
	
	
	

}
