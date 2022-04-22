package com.creator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creator.model.Ratings;
import com.creator.repository.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	//add rating
	public Ratings addRating(Ratings rating) {
		return ratingRepository.save(rating);
	}
	
	//view all ratings
	public List<Ratings> allRatings(){
		List<Ratings> ratings = ratingRepository.findAll();
		System.out.println("Getting Ratings from data base:"+ratings);
		return ratings;
	}
}
