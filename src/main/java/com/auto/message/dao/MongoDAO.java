package com.auto.message.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.auto.message.dto.MongoTestDTO;

public class MongoDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private MongoService mongoService;
	/*
	 * private boolean insert(MongoTestDTO mValue) {
	 * 
	 * boolean isSuccess = false;
	 * 
	 * isSuccess = mongoTemplate.insert(mValue, "test_collection");
	 * 
	 * return isSuccess; }
	 */
	
	private List<MongoTestDTO> findAll() {
		return mongoTemplate.findAll(MongoTestDTO.class);
	}
	
}
