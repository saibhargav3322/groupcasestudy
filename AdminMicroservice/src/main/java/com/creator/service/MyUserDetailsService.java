package com.creator.service;


import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.creator.model.Admin;
import com.creator.repository.AdminRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
      Admin user=repository.findByname(name);
       
      return new User(user.getName(),user.getPassword(),new ArrayList<>());
    	
    }
}
