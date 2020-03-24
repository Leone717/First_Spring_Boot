package com.elsospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elsospring.service.SpyService;

@RestController
public class HomeController {
	
	private String message;
	
	@Autowired
	public void setSpyService(SpyService spyService) {
	}
	
	@RequestMapping("/")
	public String index() {
		return message;
		
	}

}
