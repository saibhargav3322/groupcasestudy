package com.example.demo.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.WasherDetails;
import com.example.demo.repo.WasherRepository;

@Service
public class WasherService {
	
	String UserName;
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
	@Autowired
	private WasherRepository repository;

	public WasherDetails addWasher(WasherDetails washer) {
		return repository.insert(washer);
	}

	public List<WasherDetails> getwashers() {
		List<WasherDetails> washers = repository.findAll();
		System.out.println("Getting data from DB : " + washers);
		return washers;
	}

	public List<WasherDetails> getwasherbylocation(String location) {
		List<WasherDetails> washers = repository.findAll();
		List<WasherDetails> a=new ArrayList<>();
		for(int i=0;i<washers.size();i++)
		{
			WasherDetails w=new WasherDetails();
			w=washers.get(i);
			if(w.getLocation().equals(location))
			{
				System.out.println(w.getLocation());
				a.add(washers.get(i));
			}
		}
		return a;
	}

	public WasherDetails getdetails()
	{
		WasherDetails u=repository.findByusername(UserName);
		
		return u;
	}

	public void updateUser(WasherDetails userdetails) {
		// TODO Auto-generated method stub
		repository.save(userdetails);
	}

	public void deleteById(ObjectId id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}
}
