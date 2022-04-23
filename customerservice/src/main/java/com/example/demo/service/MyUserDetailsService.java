//package com.example.demo.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.model.Userdetails;
//import com.example.demo.repo.UserRepository;
//
//import java.util.ArrayList;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//	
//	@Autowired
//	private UserRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//      Userdetails user=repository.findByName(name);
//       
//      return new User(user.getName(),user.getPassword(),new ArrayList<>());
//    	
//    }
//}