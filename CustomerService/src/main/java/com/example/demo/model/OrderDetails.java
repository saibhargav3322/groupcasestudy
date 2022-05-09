package com.example.demo.model;

import java.util.Date;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Orderdetails")
public class OrderDetails {
	
	@Id
	Double id=Math.random();	
	String CarBrand;

	String CarModel;

	String WasherUsername="TBA";
	String customerUsername;
	WasherPack washpack;
	
	String payment;
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public WasherPack getWashpack() {
		return washpack;
	}
	public void setWashpack(WasherPack washpack) {
		this.washpack = washpack;
	}
	String date;
	AddressDetails address;
	
    String	Status="pending";
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Double getId() {
		return id;
	}
	public OrderDetails() {
		super();
	}
	public OrderDetails(String carBrand, String carModel, String washerUsername, String CustomerUsername, String date,
			AddressDetails address,String status, WasherPack w,String payment) {
		super();
		CarBrand = carBrand;
		CarModel = carModel;
		WasherUsername = washerUsername;
		this.customerUsername = CustomerUsername;
		this.date = date;
		this.address = address;
		this.Status=status;
		this.washpack=w;
		this.payment=payment;
				}
	public String getCarBrand() {
		return CarBrand;
	}
	public void setCarBrand(String carBrand) {
		CarBrand = carBrand;
	}
	public String getCarModel() {
		return CarModel;
	}
	public void setCarModel(String carModel) {
		CarModel = carModel;
	}
	public String getWasherUsername() {
		return WasherUsername;
	}
	public void setWasherUsername(String washerUsername) {
		WasherUsername = washerUsername;
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public AddressDetails getAddress() {
		return address;
	}
	public void setAddress(AddressDetails address) {
		this.address = address;
	}
	
	
	

	
	
}