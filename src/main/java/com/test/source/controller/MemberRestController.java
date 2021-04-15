package com.test.source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberRestController {

	@RequestMapping()
	public String main() {
		return "home";
	}
}
