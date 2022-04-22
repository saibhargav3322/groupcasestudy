package com.creator.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creator.model.Admin;
import com.creator.model.Ratings;
import com.creator.model.WasherPack;
import com.creator.repository.AdminRepository;
import com.creator.repository.RatingRepository;
import com.creator.repository.WasherRepository;
import com.creator.service.AdminService;
import com.creator.service.RatingService;
import com.creator.service.WasherPackService;


@RestController
@RequestMapping
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private WasherPackService washerPackService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private RatingRepository ratingRepository;
	
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
		washerRepository.save(pack);
		return "Pack saved successfully with id: "+pack.getId();
	}
	
	//get all packs
	@GetMapping("/allpacks")
	public List<WasherPack> getPack(){
		return washerRepository.findAll();
	}
	
	//delete pack by id
	@DeleteMapping("/deleteWash/{id}")
	public String deletePack(@PathVariable int id) {
		washerRepository.deleteById(id);
		return "Pack deleted with id: "+id;
	}
	
	//add rating
	@PostMapping("/addRating")
	public String saveRating(@RequestBody Ratings rating) {
		ratingRepository.save(rating);
		return "Thanks for your valuable feedback";
	}
	
	@GetMapping("/hello")
	public String sayHi() {
		return "Hello";
	}
}
