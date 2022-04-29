package com.creator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.creator.model.Admin;
import com.creator.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	ResponseEntity<String> authenticationResponse;
	
	//add an admin
	public Admin addAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	//to view all admins
	public List<Admin> allAdmins(){
		List<Admin> admins = adminRepository.findAll();
		System.out.println("Getting admins from data base "+admins);
		return admins;
	}
	
	//to view admin by id
	public Optional<Admin> adminById(int id){
		Optional<Admin> adminId = adminRepository.findById(id);
		return adminId;
	}
	
	//to delete admin
	public void deleteAdmin(Admin admin) {
		adminRepository.delete(admin);
	}
	
	//to delete admin by id
	public void deleteById(int id) {
		adminRepository.deleteById(id);
	}
	
	//for creating jwt token
	public String logintoMCs()
	{
		
	String AUTHENTICATION_URL="http://Customer-service/user/authenticate";
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	map.add("password", "sai");
	map.add("username", "sai");
	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	//RestTemplate restTemplate = new RestTemplate();
	authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
	HttpMethod.POST, request, String.class);
	if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) 
	{
		String token = "Bearer " + authenticationResponse.getBody();
	return token;
	}

	return "Invalid credential";
	}
	
	public AdminService()
	{
		
	}
	
	//for creating jwt token for washer
	public String WasherToken()
	{
		
	String AUTHENTICATION_URL="http://WASHER-SERVICE/washer/authenticate";
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	map.add("password", "raju");
	map.add("username", "raju");
	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	//RestTemplate restTemplate = new RestTemplate();
	authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
	HttpMethod.POST, request, String.class);
	if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) 
	{
		String token = "Bearer " + authenticationResponse.getBody();
	return token;
	}

	return "Invalid credential";
	}

}
