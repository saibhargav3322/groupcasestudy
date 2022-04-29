package com.example.demo.model;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;



@Document(collection="RatingData")
public class Ratings {
	

	
	ObjectId id=ObjectId.get();
	
	String customer_username;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getCustomer_username() {
		return customer_username;
	}
	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}
	public Ratings() {
		super();
	}
	public Ratings(String washer_username, String comment,
			int rating) {
		super();
		this.washer_username = washer_username;
		this.comment = comment;
		this.rating = rating;
	}
	String washer_username;
	
	String comment;
	int rating;
//public Ratings() {
//		super();
//	}
//public Ratings(String washer_username, String comment, int rating) {
//		super();
//		this.washer_username = washer_username;
//		this.comment = comment;
//		this.rating = rating;
//	}

	public String getWasher_username() {
		return washer_username;
	}
	public void setWasher_username(String washer_username) {
		this.washer_username = washer_username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
//	@Override
//	public String toString() {
//		return "Ratings [rating=" + rating + ", WasherName=" + WasherName + ", Comment=" + Comment + "]";
//	}
}
