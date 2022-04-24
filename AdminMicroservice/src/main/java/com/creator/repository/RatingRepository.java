package com.creator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.creator.model.Ratings;

public interface RatingRepository extends MongoRepository<Ratings, Integer>{

	Optional<Ratings> findBywasherid(int washerid);

	

	
}
