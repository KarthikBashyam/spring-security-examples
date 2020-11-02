package com.example.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		var user = SecurityContextHolder.getContext().getAuthentication().getName();
		return "Hello Spring Security!! " + user;
	}

}
