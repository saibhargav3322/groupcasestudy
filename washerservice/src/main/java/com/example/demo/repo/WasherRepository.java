package com.example.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.WasherDetails;

public interface WasherRepository extends MongoRepository<WasherDetails, Integer> {

	List<WasherDetails> findBylocation(String location);

	WasherDetails findByName(String name);

}
