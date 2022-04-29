package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.model.UserDetailsModel;



@Service
public class UserService {
	

	String UserName;
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
	@Autowired
	private com.example.demo.repo.UserRepository repository;
	

	public UserDetailsModel addUser(UserDetailsModel user) {
		return repository.insert(user);
	}
	
	public UserDetailsModel updateUser(UserDetailsModel user) {
		return repository.save(user);
	}

	public List<UserDetailsModel> getUsers() {
		List<UserDetailsModel> users = repository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	public Optional<UserDetailsModel> getUsersbyid(int id) {
		Optional<UserDetailsModel> users = repository.findById(id);
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	public void deleteUser(UserDetailsModel user) {
		repository.delete(user);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
		
	}
	
	public UserDetailsModel getdetails()
	{
		UserDetailsModel u=repository.findByusername(UserName);
		
		return u;
	}

	public void deleteById(ObjectId id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}
}