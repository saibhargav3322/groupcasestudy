package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ratings")
public class Ratings {
	
	@Id
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int customer_id;
	int washerid;
	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getWasherid() {
		return washerid;
	}

	public void setWasherid(int washer_id) {
		this.washerid = washer_id;
	}

	private int rating;
	private String WasherName;
	private String Comment;
	
	public Ratings() {
		
	}



	public Ratings(int id, int customer_id, int washerid, int rating, String washerName, String comment) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.washerid = washerid;
		this.rating = rating;
		WasherName = washerName;
		Comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getWasherName() {
		return WasherName;
	}

	public void setWasherName(String washerName) {
		WasherName = washerName;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	@Override
	public String toString() {
		return "Ratings [rating=" + rating + ", WasherName=" + WasherName + ", Comment=" + Comment + "]";
	}
	
}
