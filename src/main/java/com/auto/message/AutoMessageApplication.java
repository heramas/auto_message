package com.auto.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AutoMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoMessageApplication.class, args);
	}

}
