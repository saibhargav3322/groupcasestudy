package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.loginnotification;



@RestController
@RequestMapping("/Notification")
public class controller {

	
	@Autowired
	private loginnotification notification;
	
	@PostMapping("/loginnotification/{mail}" )
	public void run(@PathVariable String mail  )throws Exception {
		
		notification.sendmail(mail);
	    }
	
	@PostMapping("/orderplaced/{mail}" )
	public void order(@PathVariable String mail  )throws Exception {
		
		notification.orderplaced(mail);
	    }
	
	@PostMapping("/ordercompleted/{mail}" )
	public void completed(@PathVariable String mail  )throws Exception {
		
		notification.ordercompleted(mail);
	    }
	
		
	
	
						

	
}