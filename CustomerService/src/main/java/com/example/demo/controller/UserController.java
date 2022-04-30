package com.example.demo.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
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


import com.example.demo.exception.ApiRequestException;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.model.AddressDetails;
//import com.example.demo.jwt.JwtUtil;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.PaymentDetails;
import com.example.demo.model.Ratings;
import com.example.demo.model.UserDetailsModel;
import com.example.demo.model.WashPacks;
import com.example.demo.model.WasherPack;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.MyUserDetailsService;
//import com.example.demo.service.MyUserDetailsService;
import com.example.demo.service.UserService;
import com.google.common.base.Optional;



@RestController
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Ratings ratings;
	

	public String username;
	
	//to generate jwt token
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestParam(value="username") String username,
			@RequestParam(value="password") String password) throws Exception {

		try {
			authenticationManager.authenticate(
				//	new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
					new UsernamePasswordAuthenticationToken(username,password)
					);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				//.loadUserByUsername(authenticationRequest.getUsername());
		.loadUserByUsername(username);
		
		this.username=username;
		//service.setUserName(username);

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(jwt);
	 }
	
	//to add a customer
	 @PostMapping(value ="/adduser")
	 public UserDetailsModel saveUser(@Valid @RequestBody UserDetailsModel user) {
		 
		 Object id=ObjectId.get();
		// user.setId(id);
		 String a=user.getUsername();
		 UserDetailsModel u= repo.findByusername(a);
		 if(u == null)
		 {
	 	return service.addUser(user);
		 }
		 
		 else {
			 throw new ApiRequestException("Username already exists");
		 }
	 }
 
	 //to get list of all users
	 @GetMapping("/allusers")
	 public List<UserDetailsModel> findAllUsers() {
	 	return service.getUsers();
	 }
	
//	 //to update a existing customer details
//	 @PutMapping("/update/{id}")
//	 public ResponseEntity<Object> updateuser(@PathVariable int id,  @RequestBody UserDetailsModel user )
//	 {
//	 	 boolean isUserExist=repo.existsById(id);
//		 if(isUserExist) {
//		 	repo.save(user);
//		    	return new ResponseEntity<Object>("user Updated Successfully with id "+id,HttpStatus.OK);
//		 }
//		 else
//		 {
//			 throw new ApiRequestException("CAN NOT UPDATE AS USER NOT FOUND WITH THIS ID ::");
//		 }
//	 }
	 
	 //update his own account
	 @PutMapping("/update")
	 public ResponseEntity<Object> updateown( @RequestParam String gmail,@RequestParam String location,
			 @RequestParam String name,@RequestParam String Username,@RequestParam String password )
	 {
		 UserDetailsModel userdetails=service.getdetails();
	 	 boolean isUserExist=repo.existsById(userdetails.getId());
	 	 
		 if(isUserExist) {
			 String a=Username;
			 UserDetailsModel u= repo.findByusername(a);
			 if(u == null || a.equals(username))
			 {
				 userdetails.setGmail(gmail);
				 userdetails.setLocation(location);
				 userdetails.setName(name);
				 userdetails.setPassword(password);
				 userdetails.setUsername(username);
				 service.updateUser(userdetails);
				 return new ResponseEntity<Object>("user Updated Successfully with id "+userdetails.getId(),HttpStatus.OK);
			 }
			 
			 else {
				 throw new ApiRequestException("Username already exists");
			 }	
		 }
		 else
		 {
			 throw new ApiRequestException("CAN NOT UPDATE AS USER does not exist");
		 }
	 }
	
//	 //to get user details by id
//	 @GetMapping("/allusers/{id}")
//	 public java.util.Optional<UserDetailsModel> getuser(@PathVariable int id)
//	 		throws ApiRequestException
//	 {
//		 return service.getUsersbyid(id);
//	 }
	
	 //delete user details by id
	 @DeleteMapping("/deletebyusername/{customer_username}")
	 public String deleteuser(@PathVariable String customer_username)
	 {
			boolean isUserExist=repo.existsByusername(customer_username);
			if(isUserExist) {
				repo.deleteByusername(customer_username);
				return "user deleted with username "+customer_username;
			}
			else
			{
				return "CAN NOT DELETE AS user NOT FOUND WITH THIS username";
			}
	 }
	
	 //delete his own account
	 @DeleteMapping("/delete")
	 public ResponseEntity<Object> deleteown()
	 {
		 UserDetailsModel userdetails=service.getdetails();
	 	 boolean isUserExist=repo.existsById(userdetails.getId());
		 if(isUserExist) {
			 service.deleteById(userdetails.getId());
			 return new ResponseEntity<Object>("user deleted with id "+userdetails.getId(),HttpStatus.OK);
		 }
		 else
		 {
		 	throw new ApiRequestException("CAN NOT DELETE AS USER does not exist");
		 }
	 }
	 
	 //to get all packs
	 @GetMapping("/allpacks")
	 public List<WashPacks> getwashpacks(){
			String url="http://pack-service/washerPack/allpacks";
			WashPacks[] packs=restTemplate.getForObject(url, WashPacks[].class);
			return Arrays.asList(packs);
	 }

	 //to make payments
	 @PostMapping("/payment")
	 public String payment(@RequestBody PaymentDetails payment) {
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<PaymentDetails> entity = new HttpEntity<PaymentDetails>(payment,headers);
	      
	      return restTemplate.exchange(
	          "http://localhost:9191/payment/", HttpMethod.POST, entity, String.class).getBody();
	   }
		
	 //to add ratings for a washer by customer
		@PostMapping("/addrating")
		  public String addrating(@RequestParam String comment,@RequestParam String washer_username,@RequestParam int rating) {
//	      HttpHeaders headers = new HttpHeaders();
//	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	      HttpEntity<Ratings> entity = new HttpEntity<Ratings>(rating,headers);
//	      
//	      return restTemplate.exchange(
//	         "http://admin-service/admin/addRating", HttpMethod.POST, entity, String.class).getBody();
			ratings.setComment(comment);
			ratings.setCustomer_username(username);
			ratings.setRating(rating);
			ratings.setWasher_username(washer_username);
			String url="http://rating-service/rating/addRating";
			String response=restTemplate.postForObject(url, ratings, String.class);
			return response;
	    }
		
		//to order a wash
		@PostMapping("/addorder")
		 public String addorder(@RequestParam String carbrand,@RequestParam String carmodel,
				 @RequestParam String date,@RequestParam String housenumber,@RequestParam String streetname,@RequestParam String landmark ,@RequestParam int packid,@RequestParam String payment) {
	    
			OrderDetails order=new OrderDetails();
			AddressDetails address=new AddressDetails();
			order.setCarBrand(carbrand);
			order.setCarModel(carmodel);
			order.setCustomerUsername(username);
			order.setDate(date);
			address.setHouseNumber(housenumber);
			address.setStreetName(streetname);
			address.setLandmark(landmark);
			address.setCity(service.getdetails().getLocation());
			order.setAddress(address);
			order.setPayment(payment);
			
			String packurl="http://pack-service/washerPack/getpack/"+packid;
			
			WasherPack w=restTemplate.getForObject(packurl, WasherPack.class);
			
			order.setWashpack(w);
			
			String baseurl="http://order-service/order/addorder";
			
			String response=restTemplate.postForObject(baseurl,order, String.class);
			
			return response;
			
	   }
		
		@GetMapping("/allorders")
		public List<OrderDetails> getorders()
		{
			String baseurl="http://order-service/order/orderbyusername/"+username;
			OrderDetails[] list= restTemplate.getForObject(baseurl, OrderDetails[].class);
			return Arrays.asList(list);
		}

		//cancle the existing order
		 @DeleteMapping("/cancelorder")
		 public String deleteorder(){
			 String baseurl="http://localhost:8081/delete";
			 OrderDetails order=restTemplate.getForObject(baseurl, OrderDetails.class);
			return "Your Order is successfully Canceled "+order;
		 }

	
}