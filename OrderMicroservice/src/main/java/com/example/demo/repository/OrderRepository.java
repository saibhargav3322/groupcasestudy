package com.example.demo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.OrderDetails;

public interface OrderRepository extends MongoRepository<OrderDetails, Double>{


	List<OrderDetails> findBycustomerUsername(String username);

	boolean existsByid(Double id);

	void deleteByid(Double id);

	OrderDetails findByid(Double id);


}
