package com.example.demo.controller;

import java.util.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.types.ObjectId;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.model.OrderDetails;
import com.example.demo.model.Ratings;
import com.example.demo.model.WasherDetails;
import com.example.demo.repo.WasherRepository;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.service.WasherService;



@RestController
@RequestMapping("/washer")
@CrossOrigin("*")
public class WasherController {

	@Autowired
	private WasherService service;
	
	@Autowired
	private WasherRepository repo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private RestTemplate restTemplate;

	private String UserName;
	
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

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		this.UserName=username;
		return ResponseEntity.ok(jwt);
	 }
	
	//adding new washer
	@PostMapping(value = "/addwasher")
	public WasherDetails saveWasher(@Valid @RequestBody WasherDetails washer) {
		 String a=washer.getUsername();
		 WasherDetails u= repo.findByusername(a);
		 if(u == null)
		 {
	 	return service.addWasher(washer);
		 }
		 
		 else {
			 throw new ApiRequestException("Username already exists");
		 }
	}
	
	 //update his own account
	 @PutMapping("/update")
	 public ResponseEntity<Object> updateown( @RequestParam String gmail,@RequestParam String location,
			 @RequestParam String name,@RequestParam String Username,@RequestParam String password )
	 {
		 WasherDetails userdetails=service.getdetails();
	 	 boolean isUserExist=repo.existsById(userdetails.getId());
	 	 
		 if(isUserExist) {
			 String a=Username;
			 WasherDetails u= repo.findByusername(a);
			 if(u == null || a.equals(UserName))
			 {
				 userdetails.setGmail(gmail);
				 userdetails.setLocation(location);
				 userdetails.setName(name);
				 userdetails.setPassword(password);
				 userdetails.setUsername(Username);
				 service.updateUser(userdetails);
				 return new ResponseEntity<Object>("Washer Updated Successfully with id "+userdetails.getId(),HttpStatus.OK);
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
	 
	 //delete his own account
	 @DeleteMapping("/delete")
	 public ResponseEntity<Object> deleteown()
	 {
		 WasherDetails userdetails=service.getdetails();
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

	 //get details of all washers
	@GetMapping("/allwashers")
	public List<WasherDetails> findAllwashers() {
		return service.getwashers();
	}

	
	//get details of washers using their location
	@GetMapping("/allwashers/{location}")
	public List<WasherDetails> findwasherByAddress(@PathVariable String location) {
		return service.getwasherbylocation(location);
	}


//	@GetMapping("/allwashersbyid/{id}")
//	public Optional<WasherDetails> getwasher(@PathVariable int id)
//		throws ApiRequestException
//	{
//		return Optional.of(repo.findById(id)
//				.orElseThrow( () -> new ApiRequestException("WASHER NOT FOUND WITH THIS ID ::")));
//	}
	
	//get his rarings
	@GetMapping("/ratings")
	public List<Ratings> getallratings(){
		
		String url="http://rating-service/rating/allratings/{washer_id}";
		Map<String,String> m=new HashMap<String, String>();
		m.put("washer_id", UserName);
		Ratings[] ratings=restTemplate.getForObject(url, Ratings[].class, m);
		return Arrays.asList(ratings);
	}
	
	//get all the pending orders
	@GetMapping("/allorders")
	public List<OrderDetails> getallorders(){

		String baseurl="http://order-service/order/orderbylocation/"+service.getdetails().getLocation();
		
		OrderDetails[] l=restTemplate.getForObject(baseurl, OrderDetails[].class);
		
		return Arrays.asList(l);
	}
	
	@GetMapping("/acceptanorder/{id}")
	public String accept(@PathVariable ObjectId id)
	{
		String baseurl="http://order-service/order/changeaccepted/"+id+"/"+UserName;
//		Map<String,ObjectId> m=new HashMap<String, ObjectId>();
//		m.put("id", id );
		
		String s=restTemplate.getForObject(baseurl, String.class);
		return s;
	}
	
	@GetMapping("/completeanorder/{id}")
	public String completed(@PathVariable ObjectId id)
	{
		String baseurl="http://order-service/order/changecompleted/"+id;		
		String s=restTemplate.getForObject(baseurl, String.class);
		return s;
	}
	
	
	@DeleteMapping("/deletebyusername/{washer_username}")
	public String deletewasher(@PathVariable String washer_username)
	{
		boolean isUserExist=repo.existsByusername(washer_username);
		if(isUserExist) {
			repo.deleteByusername(washer_username);
			return "washer deleted with username "+washer_username;
		}
		else
		{
			return "CAN NOT DELETE AS WASHER NOT FOUND WITH THIS username";
		}
	}
}
