package com.example.demo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.OrderDetails;

public interface OrderRepository extends MongoRepository<OrderDetails, Integer>{


	List<OrderDetails> findBycustomerUsername(String username);

	boolean existsByid(ObjectId id);

	void deleteByid(ObjectId id);

	OrderDetails findByid(ObjectId id);


}
