package com.example.demo.model;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.executable.ValidateOnExecution;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

@Document(collection="Userdata")
public class Userdetails {
	
	@Id
	
	long id;
	
	@NotEmpty(message = "Name must not be empty")
	String name;
	
	@NotEmpty(message = "Location must not be empty")
	String location;
	
	@NotEmpty(message = "Password must not be empty")
	String password;

	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	
	public Userdetails(long id, String name, String location,String password) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.password = password;
	}
	public Userdetails() {
		super();
	}
	
	@Override
	public String toString() {
		return "Userdetails [id=" + id + ", name=" + name + ", location=" + location + ", password=" + password + "]";
	}
	
	
}
