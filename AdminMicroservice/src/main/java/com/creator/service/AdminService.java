package com.creator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creator.model.Admin;
import com.creator.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
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

}
