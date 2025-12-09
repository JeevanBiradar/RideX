package com.alpha.RideX.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String licenseNo;
	private String upiid;
	private String name;
	private String status;
	private int age;
	private long mobno;
	private String gender;
	private String mailid;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_id")
	@JsonManagedReference
	private Vechile v;
	
		
	public Driver() {
		super();
	}


	public Driver(int id, String licenseNo, String upiid, String name, String status, int age, long mobno,
			String gender,String mailid ,Vechile v) {
		super();
		this.id = id;
		this.licenseNo = licenseNo;
		this.upiid = upiid;
		this.name = name;
		this.status = status;
		this.age = age;
		this.mobno = mobno;
		this.gender = gender;
		this.mailid = mailid;
		this.v = v;
	}


	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLicenseNo() {
		return licenseNo;
	}


	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}


	public String getUpiid() {
		return upiid;
	}


	public void setUpiid(String upiid) {
		this.upiid = upiid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public long getMobno() {
		return mobno;
	}


	public void setMobno(long mobno) {
		this.mobno = mobno;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMailid() {
		return mailid;
	}


	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	
	public Vechile getV() {
		return v;
	}


	public void setV(Vechile v) {
		this.v = v;
	}


	@Override
	public String toString() {
		return "Driver [id=" + id + ", licenseNo=" + licenseNo + ", upiid=" + upiid + ", name=" + name + ", status="
				+ status + ", age=" + age + ", mobno=" + mobno + ", gender=" + gender + ", mailid=" + mailid + ", v="
				+ v + "]";
	}

}
