package com.creator.model;

import org.springframework.data.annotation.Id;

public class Ratings {
	
	@Id
	private int rating;
	private String WasherName;
	private String Comment;
	
	public Ratings() {
		
	}

	public Ratings(int rating, String washerName, String comment) {
		super();
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
