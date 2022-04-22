package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
//import com.example.demo.model.AuthenticationRequest;
//import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.Ratings;
import com.example.demo.model.WasherDetails;
import com.example.demo.repo.WasherRepository;
import com.example.demo.service.MyUserDetailsService;
//import com.example.demo.service.MyUserDetailsService;
import com.example.demo.service.WasherService;
import com.example.demo.util.JwtUtil;



@RestController
@RequestMapping("/washer")

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
	private RestTemplate restTemplate;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	
   @PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping(value = "/addwasher")
	public WasherDetails saveWasher(@Valid @RequestBody WasherDetails washer) {
		return service.addWasher(washer);
	}

	@GetMapping("/allwashers")
	public List<WasherDetails> findAllwashers() {
		return service.getwashers();
	}

	@GetMapping("/allwashers/{location}")
	public Optional<WasherDetails> findwasherByAddress(@PathVariable String location) {
		return service.getwasherbylocation(location);
	}

	@DeleteMapping(value="/delete")
	public WasherDetails removeUser(@RequestBody WasherDetails washer) {
		service.deletewasher(washer);
		return washer;
	}
	@GetMapping("/allwashersbyid/{id}")
	public Optional<WasherDetails> getwasher(@PathVariable int id)
		throws ApiRequestException
	{
		return Optional.of(repo.findById(id)
				.orElseThrow( () -> new ApiRequestException("WASHER NOT FOUND WITH THIS ID ::")));
	}
	
	@GetMapping("/allratings")
	public List<Ratings> getallratings(){
		String baseurl="http://localhost:7070/admin/allratings";
		Ratings[] allratings=restTemplate.getForObject(baseurl, Ratings[].class);
		
		return Arrays.asList(allratings);
	}
	
	@GetMapping("/allorders")
	public List<OrderDetails> getallorders(){
		String baseurl="http://localhost:8081/allorders";
		OrderDetails[] allorders=restTemplate.getForObject(baseurl, OrderDetails[].class);
		
		return Arrays.asList(allorders);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deletewasher(@PathVariable int id)
	{
		boolean isUserExist=repo.existsById(id);
		if(isUserExist) {
			repo.deleteById(id);
			return new ResponseEntity<Object>("user deleted with id "+id,HttpStatus.OK);
		}
		else
		{
			throw new ApiRequestException("CAN NOT DELETE AS WASHER NOT FOUND WITH THIS ID ::");
		}
	}
}
