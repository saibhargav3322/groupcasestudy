package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.WasherDetails;
import com.example.demo.repo.WasherRepository;
import com.example.demo.service.WasherService;

@SpringBootTest
class WasherserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private WasherService service;



	@MockBean
	private  WasherRepository repository;

	
	//addwasher
	@Test
	public void addWasherTest() {
		WasherDetails washer = new WasherDetails( "raj","Vij","raj123","raj@gmail.com","raj");
		when(repository.insert(washer)).thenReturn(washer);
		assertEquals(washer, service.addWasher(washer));
		}
	
	
//	//updatewasher
//		@Test
//		public void updateWasherTest1() {
//			
//			WasherDetails washer = new WasherDetails( "raj","Vij","raj123","raj@gmail.com","raj");
//			when(repository.save(washer)).thenReturn(washer);
//			assertEquals(washer, service.addWasher(washer));
//			//assertEquals(washer,then(service).should(times(1)).updateUser(any(WasherDetails.class)));
//			
//			}
		
	//allwashers
	@Test
	public void getallWashersTest() {
	when(repository.findAll()).thenReturn(Stream
	.of(new WasherDetails( "raj","Vij","raj123","raj@gmail.com","raj"),
	new WasherDetails( "lucky","elr","lucky123","lucky@gmail.com","lucky"),
	new WasherDetails( "Rocky","rjy","rocky123","Rocky@gmail.com","Rocky")).collect(Collectors.toList()));
	assertEquals(3, service.getwashers().size());
	}
	
	//allwashers/{location}
	@Test
	public void getWasherbylocationTest() {
		String location= "vij";
		when(service.getwasherbylocation(location))
		  .thenReturn(Stream.of(new WasherDetails("raj","vij","raj123","raj@gmail.com","raj"),
					new WasherDetails( "lucky","vi","lucky123","lucky@gmail.com","lucky"),
					new WasherDetails( "Rocky","vi","rocky123","Rocky@gmail.com","Rocky")).collect(Collectors.toList()));
		assertEquals(1, service.getwasherbylocation(location).size());
	}
	//allwashers/{location}
		@Test
		public void getWasherbylocationTest1() {
			String location= "vij";
			when(service.getwasherbylocation(location))
			  .thenReturn(Stream.of(new WasherDetails("raj","vij","raj123","raj@gmail.com","raj"),
						new WasherDetails( "lucky","vij","lucky123","lucky@gmail.com","lucky"),
						new WasherDetails( "Rocky","vij","rocky123","Rocky@gmail.com","Rocky")).collect(Collectors.toList()));
			assertEquals(3, service.getwasherbylocation(location).size());
		}
		
		
		
	//delete
	@Test
	public void deleteWasherTest() {
		WasherDetails washer = new WasherDetails( "raj","Vij","raj123","raj@gmail.com","raj");
		repository.deleteByusername(washer.getUsername());
		verify(repository, times(1)).deleteByusername(washer.getUsername());
		}
	
}
