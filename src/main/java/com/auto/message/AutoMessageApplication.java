package com.auto.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.auto.message.component.MailSendComponent;
import com.auto.message.dto.Member;
import com.auto.message.dto.MongoTestDTO;
import com.auto.message.service.MemberMapper;

@EnableCaching
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class AutoMessageApplication implements CommandLineRunner{

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private MailSendComponent mailsender;
	
	public static void main(String[] args) {
	        SpringApplication.run(AutoMessageApplication.class, args);
	    }
	
	@Override
	public void run(String... args) throws Exception {
		
		List<Member> mList = mapper.selectMemberList();
		System.out.println("mysql : " + mList);
		
		List<MongoTestDTO> result = mongoTemplate.findAll(MongoTestDTO.class);
		System.out.println("mongo : " + result); 
		
//		mailsender.sendMail("ga11106@naver.com", "제목", "내용");
	}
	
}
