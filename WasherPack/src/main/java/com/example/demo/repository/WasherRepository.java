package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.WasherPack;



public interface WasherRepository extends MongoRepository<WasherPack, Integer>{

}
