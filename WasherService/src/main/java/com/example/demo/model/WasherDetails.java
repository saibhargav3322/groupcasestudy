package com.example.demo.model;

import javax.validation.constraints.NotEmpty;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Washerdata")
public class WasherDetails {

	@Id
	ObjectId id=ObjectId.get();
	
	@NotEmpty(message = "Name must not be empty")
	String name;
	
	@NotEmpty(message = "Location must not be empty")
	String location;
	
	@NotEmpty(message = "Password must not be empty")
	String password;
	
	String gmail;
	String username;
	
	public ObjectId getId() {
		return id;
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
	public WasherDetails(String name, String location, String password, String Gmail,String Username) {
		super();
		this.name = name;
		this.location = location;
		this.password = password;
		this.gmail= Gmail;
		this.username= Username;
	}
	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public WasherDetails() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WasherDetails [id=" + id + ", name=" + name + ", location=" + location + ", password=" + password + "]";
	}
	

	
}
