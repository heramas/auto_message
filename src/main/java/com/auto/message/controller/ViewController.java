package com.auto.message.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

	String a;

	@Value("${spring.profiles.active}")
	private String profileActive;
	
	@Value("${custom.title}")
	private String title;
	
	@GetMapping("/title")
	public String title() {
		return title + " - " + profileActive;
	}
	
	@RequestMapping(value = "/" , method = RequestMethod.POST)
	public String main(String str) {
		str = Objects.toString(str, "null이 들어갈 수 없다.");
		this.a = Objects.requireNonNull(str, "not null");

		return a;
	}
}
