package com.auto.message.controller;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auto.message.component.MailSendComponent;
import com.auto.message.dto.Member;
import com.auto.message.dto.MongoTestDTO;
import com.auto.message.service.MongoService;
import com.auto.message.utils.PushUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
//@RequestMapping(value = "filter")
public class MessageController {
	
	@Autowired
	MongoService mongoService;
	
	@Autowired
	private MailSendComponent mailsender;
	
	String a;
	
	@Value("${propertis.push.key}")
	private String pushKey;
	@Value("${spring.profiles.active}")
	private String profileActive;
	@Value("${custom.title}") // 프로퍼티에 저장한 값 가져오기
	private String title;
	
	@GetMapping("/title")
	public String title() {
		return title + " - " + profileActive;
	}
	
	private List<String> list;
	
	@RequestMapping(value = "/mongotest")
	public List<MongoTestDTO> mongoTest() {
		
		List<MongoTestDTO> data = mongoService.selectData();
		System.out.println(data);
		return data;
	}
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String main(String str) {
		str = Objects.toString(str, "null이 들어갈 수 없다."); // 삼항연산자와 같은 함수
		this.a = Objects.requireNonNull(str, "not null");  //  값이 null인 경우 에러메시지 설정한 값 그대로 반환

		return a;
	}
	
	// id는 내가 어떤 값을 넣어도 그대로 반환한다
	@RequestMapping(value = "/choice/{id}", method = RequestMethod.GET)
	public String callMain (@PathVariable (value="id") String id) {
		
		return "callMain method"+id;
	}
	
	// 캐시 테스트로, 처음 호출한 값과 이후 호출이 같은 경우에 메모리에 담긴 값을 바로 반환
	@Cacheable(value = "test")
	@RequestMapping(value = "/filter/main" , method = RequestMethod.GET)
	public String filterMain(String info) {
		
		double a = System.currentTimeMillis();

		try {
			Thread.sleep(3000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		double b = System.currentTimeMillis();
		
		System.out.println("출력 : ["+info+"] - "+(b-a)/1000+"초");
		
		return info;

	}
	
	// 캐시 메모리 초기화
	@CacheEvict(value = "test")
	public void createInformation(String info) {
		list.add(info);
	}
	
	@RequestMapping(value = "/mail/send", method = RequestMethod.POST )
	public void mailSenderController(HttpServletRequest request,
									 HttpServletResponse response,
									 @RequestParam(value = "toAddr"	, required = true) String toAddr,
									 @RequestParam(value = "subject", required = true) String subject,
									 @RequestParam(value = "body"	, required = true) String body		) {
		
		mailsender.sendMail(toAddr, subject, body);
		log.info("## 메일 전송 완료");
	}
	
	@RequestMapping(value = "/push/send", method = RequestMethod.POST )
	public void pushSenderController(HttpServletRequest request,
									 HttpServletResponse response,
									 @RequestParam(value = "subject", required = true) String subject,
									 @RequestParam(value = "body"	, required = true) String body		) {
		
		String token = "";
		
		PushUtil pushUtil = new PushUtil();
		pushUtil.setKey(pushKey);
		
		String 				notifications 	= pushUtil.PeriodicNotificationJson(token, subject, body);
		HttpEntity<String> 	req 			= new HttpEntity<String>(notifications);
		 
		System.out.println("## req : " + req);
		
		CompletableFuture<String> pushThread = pushUtil.send(req);
		CompletableFuture.allOf(pushThread).join();
		 
		try {
			String rsp = pushThread.get();
			System.out.println("## success_response : " + rsp + ",  https_status : "+HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("## error_response : " + e + ",  https_status : " + HttpStatus.BAD_REQUEST);
		}
		 
		System.out.println("## 푸쉬 전송 완료");
	}
	
	
	
	public static void main(String[] args) {
		double a = System.currentTimeMillis();
		ExpressionParser p = new SpelExpressionParser();
		
//		Expression e = p.parseExpression("'Hello World!'.concat('!')");
//		String ms = (String) e.getValue();
//		System.out.println(ms);
		
//		Expression e = p.parseExpression("'Hello World!'.bytes");
//		byte[] bytes = (byte[]) e.getValue();
//		System.out.println(bytes);
		
//		Expression e = p.parseExpression("'Hello World!'.bytes.length");
//		int length = (Integer) e.getValue();
//		System.out.println(length);
		
//		Expression e = p.parseExpression("new String('hello world!').toUpperCase");
//		String s = (String) e.getValue();
//		System.out.println(s);

		
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);
		Member m = new Member("serbian",c.getTime(), "calls");
		Expression e = p.parseExpression("name");
		EvaluationContext con = new StandardEvaluationContext(m);
		
		String name	 = (String) e.getValue(con);
		double b	 = System.currentTimeMillis();
		
		System.out.println(name + " - 시간 : " +(b-a)/1000 + "초");
		
	}

}
