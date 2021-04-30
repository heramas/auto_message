package com.auto.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.auto.message.dto.MongoTestDTO;

@Service
public class MongoService {

	MongoTemplate mongoTemplate;
	
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoTestDTO> selectData() {
		List<MongoTestDTO> data = mongoTemplate.findAll(MongoTestDTO.class);
		return data;
	}
	
}
