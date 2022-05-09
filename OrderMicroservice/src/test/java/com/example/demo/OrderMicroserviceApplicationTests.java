package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.AddressDetails;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.WasherPack;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

@SpringBootTest
class OrderMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private OrderService orderService;
	
	@MockBean
	private OrderRepository orderRepository;
	
	// test All admin are save correctly
		@Test
		public void saveAdminTest() {
		OrderDetails order = new OrderDetails("BMW","volto","sudheer","sudheer","2/5/2022",new AddressDetails("24","New Colony","School","Ranchi"),"pending",new WasherPack(1,"Pack",1500,"Good"),"success");
		when(orderRepository.save(order)).thenReturn(order);
		assertEquals(order, orderService.addorder(order));
		}
	
	//testing to get all orders
	@Test
	public void getAllOrders() {
		when(orderRepository.findAll()).thenReturn(Stream
				.of(new OrderDetails("BMW","volto","sudheer","sudheer","2/5/2022",new AddressDetails("24","New Colony","School","Ranchi"),"pending",new WasherPack(1,"Pack",1500,"Good"),"success"))
				.collect(Collectors.toList()));
				assertEquals(1, orderRepository.findAll().size());
				}
	
	@Test
	public void getWasherbylocationTest() {
		String location= "Ranchi";
		when(orderService.getorderbylocation(location))
		  .thenReturn(Stream.of(new OrderDetails("swift","volto","sudheer","sudheer","2/5/2022",new AddressDetails("24","New Colony","School","Ranchi"),"pending",new WasherPack(1,"Pack",1500,"Good"),"success"))
					.collect(Collectors.toList()));
		assertEquals(1, orderService.getorderbylocation(location).size());
	}

	
}


