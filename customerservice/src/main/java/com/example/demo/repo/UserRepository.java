package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Userdetails;



public interface UserRepository extends MongoRepository<Userdetails, Integer> {

	Userdetails findByName(String name);


}

