package com.auto.message;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.auto.message.dto.MongoTestDTO;

@EnableCaching
@SpringBootApplication
@ServletComponentScan
public class AutoMessageApplication implements CommandLineRunner{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
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

	@Override
	public void run(String... args) throws Exception {
		
		MongoTestDTO mongoDTO1 = new MongoTestDTO("춘식이","01012358899","서울시 강남구","chun:)@naver.com");
		MongoTestDTO mongoDTO2 = new MongoTestDTO("춘식이2","01112358899","서울시 강남구 선릉","chun:)2@naver.com");
		
		mongoTemplate.insert(Arrays.asList(mongoDTO1,mongoDTO2), MongoTestDTO.class);
		
		List<MongoTestDTO> result = mongoTemplate.findAll(MongoTestDTO.class);
		
		System.out.println(result);
	}
}
