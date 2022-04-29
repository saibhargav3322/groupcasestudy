package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PackData")
public class WasherPack {
	@Id
	private int id;
	private String name;
	private int cost;
	private String description;
	
	
	public WasherPack() {
		
	}

	public WasherPack(int id, String name, int cost, String description) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.description = description;
		
	}

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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
