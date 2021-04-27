package com.auto.message.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
	
	
	public MongoTestDTO findOne(MongoTestDTO mDto) {
		
		MongoTestDTO mongoTestDTO = null;
		
		Query 	 query 		= null;
		Criteria criteria 	= null;
		
		try {
			query 	 = new Query();
			criteria = new Criteria();
			
			criteria = Criteria.where("name").is(mDto.getName());
			query.addCriteria(criteria);
			
			mongoTestDTO = mongoTemplate.findOne(query, MongoTestDTO.class);
			
		} catch (Exception e) {
			
		}
		
		return mongoTestDTO;
	}
}
