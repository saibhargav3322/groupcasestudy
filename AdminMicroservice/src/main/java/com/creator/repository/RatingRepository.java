package com.creator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.creator.model.Ratings;

public interface RatingRepository extends MongoRepository<Ratings, Integer>{

}
