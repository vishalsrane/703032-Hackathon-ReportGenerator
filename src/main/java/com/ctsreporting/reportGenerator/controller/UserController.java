package com.ctsreporting.reportGenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctsreporting.reportGenerator.model.Associate;
import com.ctsreporting.reportGenerator.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<Associate> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping
	public Associate createUser(@RequestBody Associate associate) {
		return userService.createUser(associate);
	}
	

}
