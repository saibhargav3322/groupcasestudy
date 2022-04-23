package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Check {
	
	@GetMapping("/check")
	public String method()
	{
		return " Hello from Washer, This is for checking purpose";
	}

}
