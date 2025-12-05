package com.alpha.RideX.DTO;

public class DriverVechileDTO {

	private int id;
	private String licenseNo;
	private String upiid;
	private String name;
	private int age;
	private long mobno;
	private String gender;
	private String mailid;
	private String vname;
	private String vno;
	private String vtype;
	private String model;
	private int capacity;
	private String currentcity;
	private int priceperkm;
	
	public DriverVechileDTO(int id, String licenseNo, String upiid, String name, int age, long mobno,
			String gender, String mailid, String vname, String vno, String vtype, String model, int capacity,
			String currentcity, int priceperkm) {
		super();
		this.id = id;
		this.licenseNo = licenseNo;
		this.upiid = upiid;
		this.name = name;
		this.age = age;
		this.mobno = mobno;
		this.gender = gender;
		this.mailid = mailid;
		this.vname = vname;
		this.vno = vno;
		this.vtype = vtype;
		this.model = model;
		this.capacity = capacity;
		this.currentcity = currentcity;
		this.priceperkm = priceperkm;
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

	public int getPriceperkm() {
		return priceperkm;
	}

	public void setPriceperkm(int priceperkm) {
		this.priceperkm = priceperkm;
	}

	@Override
	public String toString() {
		return "DriverVechileDTO [id=" + id + ", licenseNo=" + licenseNo + ", upiid=" + upiid + ", name=" + name
				+ ", age=" + age + ", mobno=" + mobno + ", gender=" + gender + ", mailid="
				+ mailid + ", vname=" + vname + ", vno=" + vno + ", vtype=" + vtype + ", model=" + model + ", capacity="
				+ capacity + ", currentcity=" + currentcity + ", priceperkm=" + priceperkm + "]";
	}
	
	
	
	
}
