package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.WasherDetails;

public interface WasherRepository extends MongoRepository<WasherDetails, Integer> {

	Optional<WasherDetails> findBylocation(String location);

	WasherDetails findByName(String name);

}
