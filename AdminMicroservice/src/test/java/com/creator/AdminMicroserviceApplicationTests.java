package com.creator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.creator.model.Admin;
import com.creator.repository.AdminRepository;
import com.creator.service.AdminService;


@SpringBootTest
class AdminMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private AdminService adminService;
	
	@MockBean
	private AdminRepository adminRepository;
	
	
	//testing to get all admins
	@Test
	public void getAdminTest() {
	when(adminRepository.findAll()).thenReturn(Stream
	.of(new Admin(101,"Vamsi","V@123"),
	new Admin(102,"Bhargav","B@456"),
	new Admin(103,"Gowtham","G@789")).collect(Collectors.toList()));
	assertEquals(3, adminService.allAdmins().size());
	}
	
	//testing to save admin
	@Test
	public void saveAdmin() {
		Admin admin = new Admin(104,"Shrawanth","S@159");
		when(adminRepository.save(admin)).thenReturn(admin);
		assertEquals(admin, adminService.addAdmin(admin));
	}
	
	//testing to delete admin
	@Test
	public void deleteAdmin() {
		Admin admin = new Admin(104,"Shrawanth","S@159");
		adminService.deleteAdmin(admin);
		verify(adminRepository, times(1)).delete(admin);
	}

}
