package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderDetails;
import com.example.demo.repository.OrderRepository;


@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	

	public OrderDetails addorder(OrderDetails order) {
		return repository.save(order);
	}

	public List<OrderDetails> getUsers() {
		List<OrderDetails> orders = repository.findAll();
		System.out.println("Getting data from DB : " + orders);
		return orders;
	}
	public void deleteUser(OrderDetails user) {
		repository.delete(user);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
		
	}
}
