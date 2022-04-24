package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Washerdata")
public class WasherDetails {

	@Id
	int id;
	
	String name;
	String location;
	String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public WasherDetails(int id, String name, String location, String password) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.password = password;
	}
	public WasherDetails() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WasherDetails [id=" + id + ", name=" + name + ", location=" + location + ", password=" + password + "]";
	}
	

	
}
