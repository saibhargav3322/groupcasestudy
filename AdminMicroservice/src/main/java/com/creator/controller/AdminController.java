package com.creator.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.creator.model.Admin;
import com.creator.model.OrderDetails;
import com.creator.model.Ratings;
import com.creator.model.Userdetails;
import com.creator.model.WasherDetails;
import com.creator.model.WasherPack;
import com.creator.repository.AdminRepository;
import com.creator.service.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminService adminService;
	
	ResponseEntity<String> authenticationResponse;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//String token;
	
	//adding an admin
	@PostMapping("/addadmin")
	public Admin addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);
	}
	
	//get all admins
	@GetMapping("/alladmins")
	public List<Admin> findAllAdmins(){
		return adminService.allAdmins();
	}
	
	//updating admins by id
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateAdmin(@PathVariable int id, @RequestBody Admin admin){
		adminRepository.save(admin);
		return new ResponseEntity<Object>(id,HttpStatus.OK);
	}
	
	//delete admin by id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable int id){
		adminRepository.deleteById(id);
		return new ResponseEntity<Object>(id,HttpStatus.OK);
	}
	
	//get admin by id
	@GetMapping("/adminById/{id}")
	public Optional<Admin> findAdminById(@PathVariable int id){
		return adminService.adminById(id);
	}
	
	//add pack
	@PostMapping("/addpack")
	public String savePack(@RequestBody WasherPack pack) {
		String url="http://pack-service/washerPack/addpack";
		String response=restTemplate.postForObject(url, pack, String.class);
		return response;
	}
	
	//get all packs
	@GetMapping("/allpacks")
	public List<WasherPack> getPack(){
		String url="http://pack-service/washerPack/allpacks";
		WasherPack[] packs=restTemplate.getForObject(url, WasherPack[].class);
		return Arrays.asList(packs);
	}
	
	//delete pack by id
	@DeleteMapping("/deleteWashPack/{id}")
	public String deletePack(@PathVariable int id) {

		String url="http://pack-service/washerPack/deleteWash/{id}";
		Map<String,Integer> m=new HashMap<String, Integer>();
		m.put("id",id);
		restTemplate.delete(url,m);
		return "Deleted pack with id"+id;
	}
	
	//get all ratings
	@GetMapping("/allratings")
	public List<Ratings> getRatings(){
		String url="http://rating-service/rating/allratings";
		Ratings[] ratings=restTemplate.getForObject(url, Ratings[].class);
		return Arrays.asList(ratings);
	}
	
	//get all ratings by id
	@GetMapping("/allratings/{washer_username}")
	public List<Ratings> getRatingsbyid(@PathVariable String washer_username){
		
		String url="http://rating-service/rating/allratings/{washer_id}";
		Map<String,String> m=new HashMap<String, String>();
		m.put("washer_id", washer_username);
		Ratings[] ratings=restTemplate.getForObject(url, Ratings[].class, m);
		return Arrays.asList(ratings);
	}
	
	
	//all customers
	@GetMapping("/allcustomers")
	public List<Userdetails> findAllUsers()
	{
		String token=adminService.logintoMCs();
	//	System.out.println(token);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		//RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		String baseurl="http://Customer-service/user/allusers";
		ResponseEntity<Userdetails[]> helloResponse = restTemplate.exchange(baseurl, HttpMethod.GET, jwtEntity,
				Userdetails[].class);
		return Arrays.asList(helloResponse.getBody());
	}
	
	//all washers
	@GetMapping("/allwashers")
	public List<WasherDetails> findAllWashers()
	{
		String token=adminService.WasherToken();
		//System.out.println(token);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		//RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		String baseurl="http://WASHER-SERVICE/washer/allwashers";
		ResponseEntity<WasherDetails[]> helloResponse = restTemplate.exchange(baseurl, HttpMethod.GET, jwtEntity,
				WasherDetails[].class);
		return Arrays.asList(helloResponse.getBody());
	}
	
	//all orders
	@GetMapping("/allorders")
	public List<OrderDetails> getallorders(){
		String baseurl="http://localhost:8081/allorders";
		OrderDetails[] allorders=restTemplate.getForObject(baseurl, OrderDetails[].class);
		
		return Arrays.asList(allorders);
	}
	
	@DeleteMapping("/deletewasherbyusername")
	public String deletewasher(@RequestParam String username)
	{
		String token=adminService.WasherToken();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		//RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		String baseurl="http://WASHER-SERVICE/washer/deletebyusername/"+username;
		ResponseEntity<String> helloResponse = restTemplate.exchange(baseurl, HttpMethod.DELETE, jwtEntity,String.class);

		if(helloResponse.getBody().contains("washer deleted with username "))
		{
		return "deleted with username"+username;
		}
		else
		{
			return "username not found";
		}
		}
	
	@DeleteMapping("/deletecustomerbyusername")
	public String deleteuser(@RequestParam String username)
	{
		String token=adminService.logintoMCs();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		//RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		String baseurl="http://Customer-service/user/deletebyusername/"+username;
		ResponseEntity<String> helloResponse = restTemplate.exchange(baseurl, HttpMethod.DELETE, jwtEntity,String.class);

		if(helloResponse.getBody().contains("user deleted with username "))
		{
		return "deleted with username"+username;
		}
		else
		{
			return "username not found";
		}
		}
	}

	
//	@PostMapping("/createtoken")
//	public String logintoMCs()
//	{
//		
//		
//		//ResponseEntity<String> authenticationResponse ;
//	String AUTHENTICATION_URL="http://localhost:9095/user/authenticate";
//	HttpHeaders headers = new HttpHeaders();
//	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//	
////	String username="north";
////	String password="north";
//
//
//	MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//	map.add("password", "north");
//	map.add("username", "north");
//
//
//	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//
//	
//	//ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
//	RestTemplate restTemplate = new RestTemplate();
//	authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
//	HttpMethod.POST, request, String.class);
//	System.out.println("hello");
////	System.out.println(authenticationResponse.getBody());
//	if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) 
//	{
//		token = "Bearer " + authenticationResponse.getBody();
//	System.out.println(token);
//	return "Generated";
//	}
//
//	return "Invalid credential";
//	}

