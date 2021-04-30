package com.auto.message.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@MapperScan(value={"com.auto.message.service"})
public class DatabaseConnectConfig {

	/*
	 * mongoDB 연결하기 위한 bean 생성
	 */
	@Value("${spring.data.mongodb.uri}")
	private String mongoUrl;
	
	@Bean
	public MongoDatabaseFactory databaseFactory() {
		return new SimpleMongoClientDatabaseFactory(mongoUrl);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(databaseFactory());
	}
	
	/*
	 * mybatis 연동을 위한 bean 생성
	 */
	@Bean
	public SqlSessionTemplate sessionTemplate(SqlSessionFactory factory) {
		return new SqlSessionTemplate(factory);
	}
	
	@Bean
	public SqlSessionFactory factory(DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);

		Resource res = new PathMatchingResourcePatternResolver().getResource("classpath:mappers/UserMapper.xml");
		bean.setMapperLocations(res);
		
		return bean.getObject();
	}
	
	
}
