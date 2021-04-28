package com.auto.message.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "test_collection")
public class MongoTestDTO {
	
	@Id
	private String id;
	
	private String name;
	private String number;
	private String addr;
	private String email;
	
	public MongoTestDTO(String name, String number, String addr, String email) {
		this.name   = name;
		this.number = number;
		this.addr   = addr;
		this.email  = email;
	}
}
