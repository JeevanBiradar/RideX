package com.alpha.RideX.DTO;

public class RegDriverVehicleDTO {

	
	private String licenceNo;
	private String upiId;
	private String name;
	private int age;
	private long mobileNo;
	private String gender;
	private String mail;
	private String vehicleName;
	private String vehicleNo;
	private String type;
	private String model;
	private int capacity;
	private double longitude;
	private double latitude;
	private double pricePerKm;
	private int averageSpeed;
	
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
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
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getPricePerKm() {
		return pricePerKm;
	}
	public void setPricePerKm(double pricePerKm) {
		this.pricePerKm = pricePerKm;
	}
	public int getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(int averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	@Override
	public String toString() {
		return "RegDriverVehicleDTO [licenceNo=" + licenceNo + ", upiId=" + upiId + ", name=" + name + ", age=" + age
				+ ", mobileNo=" + mobileNo + ", gender=" + gender + ", mail=" + mail + ", vehicleName=" + vehicleName
				+ ", VehicleNo=" + vehicleNo + ", type=" + type + ", model=" + model + ", capacity=" + capacity
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", pricePerKm=" + pricePerKm
				+ ", averageSpeed=" + averageSpeed + "]";
	}
	
	
	
}
