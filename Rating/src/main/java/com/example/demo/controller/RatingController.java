package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ratings;
import com.example.demo.repository.RatingRepository;
import com.example.demo.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/addRating")
	public String saveRating(@RequestBody Ratings rating) {
		ratingRepository.save(rating);
		return "Thanks for your valuable feedback";
	}
	
	//get all ratings
	@GetMapping("/allratings")
	public List<Ratings> getRatings(){
		return ratingRepository.findAll();
	}
	
	//get all ratings by id
	@GetMapping("/allratings/{washer_id}")
	public List<Ratings> getRatingsbyid(@PathVariable int washer_id){
		
		 return ratingService.allRatingsbyid(washer_id);
	}

}
