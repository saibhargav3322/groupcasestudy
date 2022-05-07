package com.creator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.creator.model.Admin;


public interface AdminRepository extends MongoRepository<Admin, Integer>{

	Admin findByname(String name);

}
