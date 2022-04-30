package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.UserDetailsModel;

import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

@SpringBootTest
class CustomerserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService service;



	@MockBean
	private  UserRepository repository;

	
	//adduser
	@Test
	public void addUserTest() {
		UserDetailsModel user = new UserDetailsModel( "raj","Vij","raj123","raj@gmail.com","raj");
		when(repository.insert(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
		}
	
	

		
	//all users
	@Test
	public void getallUserTest() {
	when(repository.findAll()).thenReturn(Stream
	.of(new UserDetailsModel( "raj","Vij","raj123","raj@gmail.com","raj"),
	new UserDetailsModel( "lucky","elr","lucky123","lucky@gmail.com","lucky"),
	new UserDetailsModel( "Rocky","rjy","rocky123","Rocky@gmail.com","Rocky")).collect(Collectors.toList()));
	assertEquals(3, service.getUsers().size());
	}
	
		
		
	//delete
	@Test
	public void deleteUserTest() {
		UserDetailsModel user = new UserDetailsModel( "raj","Vij","raj123","raj@gmail.com","raj");
		repository.deleteByusername(user.getUsername());
		verify(repository, times(1)).deleteByusername(user.getUsername());
		}
}