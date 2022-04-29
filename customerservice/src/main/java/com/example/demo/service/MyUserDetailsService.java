package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserDetailsModel;
import com.example.demo.repo.UserRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
      UserDetailsModel user=repository.findByusername(name);
       
      return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    	
    }
}

//Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWkiLCJleHAiOjE2NTExMDUxODAsImlhdCI6MTY1MTA2OTE4MH0.yH3g64BcSgTSN397l2ULdj5x0kcNjq89_gCoceNK1SQ