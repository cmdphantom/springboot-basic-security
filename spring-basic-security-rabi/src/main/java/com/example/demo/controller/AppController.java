package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AppController {

	@GetMapping("/getMsg")
	public String greeting() {
		return "Spring Security Example";
	}
	
}
