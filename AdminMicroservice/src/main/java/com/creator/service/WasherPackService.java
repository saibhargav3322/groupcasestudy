package com.creator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creator.model.WasherPack;
import com.creator.repository.WasherRepository;

@Service
public class WasherPackService {

	@Autowired
	private WasherRepository washerRepository;
	
	//add pack
	public WasherPack addPack(WasherPack washerPack) {
		return washerRepository.save(washerPack);
	}
	
	//get all packs 
	public List<WasherPack> getAllPacks(){
		List<WasherPack> washerPacks = washerRepository.findAll();
		return washerPacks;
	}
	
	//delete pack by id
	public void deleteById(int id) {
		washerRepository.deleteById(id);
	}
}
