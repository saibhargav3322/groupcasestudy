package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Userdetails;



@Service
public class UserService {

	@Autowired
	private com.example.demo.repo.UserRepository repository;
	

	public Userdetails addUser(Userdetails user) {
		return repository.save(user);
	}

	public List<Userdetails> getUsers() {
		List<Userdetails> users = repository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	public Optional<Userdetails> getUsersbyid(int id) {
		Optional<Userdetails> users = repository.findById(id);
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	public void deleteUser(Userdetails user) {
		repository.delete(user);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
		
	}
}