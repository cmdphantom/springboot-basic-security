package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

@RestController
//@CrossOrigin(origins="*")
//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	//M-1 response as String
	// ANGULAR CODE :: M-1 return this.http.get(url,{headers,responseType:'text' as 'json'});
	@GetMapping("/login")
	public String login() {
		return "Authenticated Successfull";
	}
	
	//M-2/M-3 response as Object.... i.e converted to JSON
	//ANGULAR CODE ::M-2 return this.http.get(url,{headers});
	/*@GetMapping("/login")
	public User login() {
		User user=new User();
		user.setName("SUCCSS");
		return user;
	}*/

	@GetMapping("/allUsers")
	public List<User> getUser() {
		User user1=new User(108, "Rabi", "rabi@g.com", "9874");
		User user2=new User(109, "Roddur", "roddur@g.com", "5678");
		User user3=new User(110, "Swagata", "swagata@g.com", "3245");
		
		List<User> lstOfUser=Stream.of(user1,user2,user3)
				.collect(Collectors.toList());

		return lstOfUser;
	}

	
}
