package com.example.demo.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

	public void deleteById(Double id) {
		repository.deleteById(id);
		
	}

	public List<OrderDetails> getorderbyusername(String username) {
		// TODO Auto-generated method stub
		List<OrderDetails> list=repository.findBycustomerUsername(username);
		return list;
	}

	public List<OrderDetails> getorderbylocation(String location) {
		// TODO Auto-generated method stub
		List<OrderDetails> l=repository.findAll();
		List<OrderDetails> l1=new ArrayList<OrderDetails>();
		for(int i=0;i<l.size();i++)
		{
			OrderDetails o=new OrderDetails();
			o=l.get(i);
			if(o.getPayment().equals("success")  && o.getAddress().getCity().equals(location))
			{
				l1.add(o);
			}
		}
		return l1;
	}

	public void deleteByid(Double id) {
		// TODO Auto-generated method stub
		repository.deleteByid(id);
		
	}

	public void update(OrderDetails o) {
		// TODO Auto-generated method stub
		repository.save(o);
		
	}
}
