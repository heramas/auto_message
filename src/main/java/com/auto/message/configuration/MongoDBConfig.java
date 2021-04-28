package com.auto.message.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

	@Value("{data.mongo.url}")
	private String connectionString;
	
	@Bean
	public MongoDatabaseFactory databaseFactory() {
		return new SimpleMongoClientDatabaseFactory(connectionString);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(databaseFactory());
	}
}
