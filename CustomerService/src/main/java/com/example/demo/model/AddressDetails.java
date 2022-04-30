package com.example.demo.model;

public class AddressDetails {

	
	String HouseNumber;
	String StreetName;
	String landmark;
	String city;
	public AddressDetails() {
		super();
	}
	public AddressDetails(String houseNumber, String streetName, String landmark, String city) {
		super();
		HouseNumber = houseNumber;
		StreetName = streetName;
		this.landmark = landmark;
		this.city = city;
	}
	public String getHouseNumber() {
		return HouseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}
	public String getStreetName() {
		return StreetName;
	}
	public void setStreetName(String streetName) {
		StreetName = streetName;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
