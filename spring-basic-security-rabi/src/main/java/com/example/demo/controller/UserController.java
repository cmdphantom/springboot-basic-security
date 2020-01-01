package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

@RestController
@CrossOrigin(origins="*")
public class UserController {
	
	@GetMapping("/")
	public String login() {
		return "Authenticated Successfull";
	}

	@GetMapping("/getUser")
	public List<User> getUser() {
		User user1=new User(108, "Rabi", "rabi@g.com", "9874");
		User user2=new User(108, "Roddur", "roddur@g.com", "5678");
		User user3=new User(108, "Swagata", "swagata@g.com", "3245");
		
		List<User> lstOfUser=Stream.of(user1,user2,user3)
				.collect(Collectors.toList());

		return lstOfUser;
	}

	
}
