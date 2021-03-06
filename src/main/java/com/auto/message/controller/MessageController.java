package com.auto.message.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.auto.message.component.MailSendComponent;
import com.auto.message.component.SearchComponent;
import com.auto.message.dto.Member;
import com.auto.message.dto.MongoTestDTO;
import com.auto.message.service.MongoService;
import com.auto.message.utils.PushUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class MessageController {
	
	@Autowired
	MongoService mongoService;
	@Autowired
	private MailSendComponent mailsender;
	@Autowired
	SearchComponent searchComponent;
	
	String a;
	
	@Value("${propertis.push.key}") // ???????????? push key ?????? ??????????????? ????????????.
	private String pushKey;
	@Value("${spring.profiles.active}")
	private String profileActive;
	@Value("${custom.title}") // ??????????????? ????????? ??? ????????????
	private String title;
	@Value("${naver.search.clientID}")
	private String clientId;
	@Value("${naver.search.clientSecret}")
	private String cSecret;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView home() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.jsp");
		return mv;
	}
	
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
		str = Objects.toString(str, ""); // ?????????????????? ?????? ??????
		this.a = Objects.requireNonNull(str, "## ???????????? null ?????? ???????????? ????????????.");  //  ?????? null??? ?????? ??????????????? ????????? ??? ????????? ??????

		return a;
	}
	
	// id??? ?????? ?????? ?????? ????????? ????????? ????????????
	@RequestMapping(value = "/choice/{id}", method = RequestMethod.GET)
	public String callMain (@PathVariable (value="id") String id) {
		
		return "callMain method"+id;
	}
	
	// ?????? ????????????, ?????? ????????? ?????? ?????? ????????? ?????? ????????? ???????????? ?????? ?????? ?????? ??????
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
		
		System.out.println("?????? : ["+info+"] - "+(b-a)/1000+"???");
		
		return info;

	}
	
	// ?????? ????????? ?????????
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
		log.info("## ?????? ?????? ??????");
	}
	
	@RequestMapping(value = "/push/send", method = RequestMethod.POST, produces = "application/json" )
	public void pushSenderController(HttpServletRequest request,
									 HttpServletResponse response,
									 @RequestParam(value = "subject", required = true) String subject,
									 @RequestParam(value = "body"	, required = true) String body		) {
		
		String token = "";
		
		PushUtil pushUtil = new PushUtil();
		pushUtil.setKey(pushKey); // ???????????? ?????? pushUtil ??? ????????? ?????????.
		
		String 				notifications 	= pushUtil.PeriodicNotificationJson(token, subject, body);
		HttpEntity<String> 	req				= new HttpEntity<String>(notifications);
		 
		System.out.println("#### [1] - ?????? ?????? ?????? : " + req);
		
		CompletableFuture<String> pushThread = pushUtil.send(req);
		CompletableFuture.allOf(pushThread).join();
		 
		try {
			String rsp = pushThread.get();
			System.out.println("#### [2] - ?????? ?????? ?????? : " + rsp + ",  https_status : " + HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("#### [2] - ?????? ?????? ?????? : " + e +   ",  https_status : " + HttpStatus.BAD_REQUEST);
		}
	
		System.out.println("#### [3] - ?????? ?????? ??????");

	}
	
	@RequestMapping(value = "/api/search", method = RequestMethod.GET)
	public HashMap<String, Object> aroundSearch(	HttpServletRequest request,
			 					HttpServletResponse response,
			 					@RequestParam(value = "searchParam", required = true) String param) throws JsonMappingException, JsonProcessingException {
		
		String 	result 		= "";
		String 	getTest 	= "";
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			getTest = URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("????????? ????????? ??????",e);
		}
		
		result = searchComponent.search(clientId, cSecret, getTest);
		
		HashMap<String, Object> setParam = new ObjectMapper().readValue(result, HashMap.class);
		
		paramMap.put("items", setParam.get("items"));
		
		return paramMap;
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
		
		System.out.println(name + " - ?????? : " +(b-a)/1000 + "???");
		
	}

}
