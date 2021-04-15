package com.auto.message.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

	@Value("${spring.profiles.active}")
	private String profileActive;
	
	@Value("${custom.title}")
	private String title;
	
	@GetMapping("/title")
	public String title() {
		return title + " - " + profileActive;
	}
	
	@RequestMapping("/")
	public String main() {
		return "demo project!!";
	}
}
