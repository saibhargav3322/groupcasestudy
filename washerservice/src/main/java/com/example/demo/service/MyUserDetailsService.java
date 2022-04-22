package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.WasherDetails;
import com.example.demo.repo.WasherRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private WasherRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
       WasherDetails washer=repository.findByName(name);
       
       return new User(washer.getName(),washer.getPassword(),new ArrayList<>());
    	
    	// return new User("user", "user",new ArrayList<>());
    }
}
	
	
