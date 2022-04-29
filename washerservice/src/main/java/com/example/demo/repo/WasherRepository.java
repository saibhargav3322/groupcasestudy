package com.example.demo.repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.WasherDetails;

public interface WasherRepository extends MongoRepository<WasherDetails, Integer> {

	WasherDetails findByName(String name);
	
	Optional<WasherDetails> findBylocation(String location);

	//WasherDetails findByName(String name);

	Optional<WasherDetails> findAllBylocation(String location);

	Optional<WasherDetails> findAllById(String location);

	WasherDetails findByusername(String userName);

	boolean existsById(ObjectId id);

	void deleteById(ObjectId id);

	boolean existsByusername(String washer_username);

	void deleteByusername(String washer_username);

}
