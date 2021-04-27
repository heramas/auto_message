package com.auto.message.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.auto.message.dto.MongoTestDTO;

public interface MongoService extends MongoRepository<MongoTestDTO, Long> {
	
	public List<MongoTestDTO> 	findAll();
	//public boolean 				insert(MongoTestDTO mValue);
	public MongoTestDTO 		findOn(MongoTestDTO mDto);
	
}
