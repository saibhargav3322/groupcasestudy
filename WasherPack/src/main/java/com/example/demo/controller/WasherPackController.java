package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.WasherPack;
import com.example.demo.repository.WasherRepository;
import com.example.demo.service.WasherPackService;




@RestController
@RequestMapping("/washerPack")
public class WasherPackController {
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private WasherPackService washerPackService;
	
	//add pack
		@PostMapping("/addpack")
		public String savePack(@RequestBody WasherPack pack) {
			washerRepository.insert(pack);
			return "Pack saved successfully with id: "+pack.getId();
		}
		
		//get all packs
		@GetMapping("/allpacks")
		public List<WasherPack> getPack(){
			return washerRepository.findAll();
		}
		
		@GetMapping("/getpack/{id}")
		public WasherPack byid(@PathVariable int id)
		{
			return washerRepository.findByid(id);
		}
		
		//delete pack by id
		@DeleteMapping("/deleteWash/{id}")
		public String deletePack(@PathVariable int id) {
			washerRepository.deleteById(id);
			return "Pack deleted with id: "+id;
		}
}
