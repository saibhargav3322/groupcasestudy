package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.WasherPack;
import com.example.demo.repository.WasherRepository;
import com.example.demo.service.WasherPackService;

@SpringBootTest
class WasherPackApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@MockBean
	private WasherRepository washerRepository;
	
	@Autowired
	private WasherPackService packService;
	
	@Test
	public void getAllPacks() {
		when(washerRepository.findAll()).thenReturn(Stream
				.of(new WasherPack(1, "Aditya",1500,"Good"),
				new WasherPack(2, "Teja",2000,"Best"),
				new WasherPack(3, "Vara",3000,"average")).collect(Collectors.toList()));
				assertEquals(3, packService.getAllPacks().size());
	}
	
	@Test
	public void addpack() {
		WasherPack washerPack = new WasherPack(4,"Raju",500,"best");
		when(washerRepository.save(washerPack)).thenReturn(washerPack);
		assertEquals(washerPack, packService.addPack(washerPack));
	}
	
	/*@Test
	public void deletePackById(int id) {
		id = 1;
		WasherPack washerPack = new WasherPack(1,"Aditya",1500,"Good");
		String actual = "Pack deleted with id: 1";
		String real = washerRepository.deleteById(id);
	}*/

}
