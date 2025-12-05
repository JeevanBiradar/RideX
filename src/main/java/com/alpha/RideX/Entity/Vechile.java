package com.alpha.RideX.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vechile {
	
	@Id
	private int id;
	private String vname;
	private String vno;
	private String vtype;
	private String model;
	private int capacity;
	private String currentcity;
	private String availablestatus;
	private int priceperkm;
	
	public Vechile() {
		super();
	}
	
	public Vechile(int id, String vname,String vno ,String vtype, String model, int capacity, String currentcity,
			String availablestatus, int priceperkm) {
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

	public int getPriceperkm() {
		return priceperkm;
	}

	public void setPriceperkm(int priceperkm) {
		this.priceperkm = priceperkm;
	}

	@Override
	public String toString() {
		return "Vechile [id=" + id + ", vname=" + vname + ", vno=" + vno + ", vtype=" + vtype + ", model=" + model
				+ ", capacity=" + capacity + ", currentcity=" + currentcity + ", availablestatus=" + availablestatus
				+ ", priceperkm=" + priceperkm + "]";
	}

	
	
	
	
	
	
	

}
