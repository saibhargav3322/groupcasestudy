package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.OrderDetails;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/addorder")
	 public String addorder( @RequestBody OrderDetails orderDetails) {
	 	orderService.addorder(orderDetails);
	 	return "Order is Placed with Washer and will be Proceesed soon "
	 			+orderDetails;
	 }
	
	@GetMapping("/allorders")
	public List<OrderDetails> getorder()
	{
		return orderRepository.findAll();
	}
	
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<Object> deletorder(@PathVariable int id)
	 {
		 boolean isOrderExist=orderRepository.existsById(id);
		 if(isOrderExist) {
			 orderService.deleteById(id);
			 return new ResponseEntity<Object>("Order deleted with id "+id,HttpStatus.OK);
		 }
		 else
		 {
			 throw new ApiRequestException("CAN NOT DELETE ORDER,AS ORDER NOT FOUND WITH THIS ID ::");
		 }
	 }
			
}