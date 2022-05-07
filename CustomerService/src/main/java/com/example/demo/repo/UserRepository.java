package com.example.demo.repo;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.UserDetailsModel;



public interface UserRepository extends MongoRepository<UserDetailsModel, Integer> {

	UserDetailsModel findByusername(String name);

	boolean existsById(ObjectId id);

	void deleteById(ObjectId id);

	boolean existsByusername(String customer_username);

	void deleteByusername(String customer_username);


}

