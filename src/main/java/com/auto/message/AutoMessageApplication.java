package com.auto.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auto.message.dto.Member;
import com.auto.message.service.MemberRepository;

@EnableCaching
@SpringBootApplication
@ServletComponentScan
public class AutoMessageApplication {
	/*
	 * private static Logger logger =
	 * LoggerFactory.getLogger(AutoMessageApplication.class);
	 * 
	 * @Autowired MemberRepository memberRepository;
	 * 
	 * @GetMapping("/member/nocache/{name}")
	 * 
	 * @ResponseBody public Member getNoCacheMember(@PathVariable String name){
	 * 
	 * long start = System.currentTimeMillis(); // 수행시간 측정 Member member =
	 * memberRepository.findByNameNoCache(name); // db 조회 long end =
	 * System.currentTimeMillis();
	 * 
	 * logger.info(name+ "의 NoCache 수행시간 : "+ Long.toString(end-start));
	 * 
	 * return member; }
	 * 
	 * @GetMapping("/member/cache/{name}")
	 * 
	 * @ResponseBody public Member getCacheMember(@PathVariable String name){
	 * 
	 * long start = System.currentTimeMillis(); // 수행시간 측정 Member member =
	 * memberRepository.findByNameCache(name); // db 조회 long end =
	 * System.currentTimeMillis();
	 * 
	 * logger.info(name+ "의 Cache 수행시간 : "+ Long.toString(end-start));
	 * 
	 * return member; }
	 * 
	 * @GetMapping("/member/refresh/{name}")
	 * 
	 * @ResponseBody public String refresh(@PathVariable String name){
	 * memberRepository.refresh(name); // 캐시제거 return "cache clear!"; }
	 * 
	 */
	   
	public static void main(String[] args) {
	        SpringApplication.run(AutoMessageApplication.class, args);
	    }
		/*
		 * @GetMapping("/")
		 * 
		 * @ResponseBody public String index(){ return "HelloWorld"; }
		 */
}
