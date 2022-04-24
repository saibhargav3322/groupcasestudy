package com.creator.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
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
	
	
	//view all ratings by id
	public List<Ratings> allRatingsbyid(int washerid){
		List<Ratings> ratings = ratingRepository.findAll();
		List<Ratings> a=new ArrayList<>();
		for(int i=0;i<ratings.size();i++)
		{
			Ratings w=new Ratings();
			w=ratings.get(i);
			if(w.getWasherid()==washerid)
			{
				//System.out.println(w.getLocation());
				a.add(ratings.get(i));
			}
		}
		return a;
	}
}
