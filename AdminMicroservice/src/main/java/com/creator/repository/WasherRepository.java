package com.creator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.creator.model.WasherPack;

public interface WasherRepository extends MongoRepository<WasherPack, Integer>{

}
