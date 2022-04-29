package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.demo.model.WasherDetails;

import com.example.demo.repo.WasherRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private WasherRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
      WasherDetails user=repository.findByusername(name);
       
      return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    	
    }
}