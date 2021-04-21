package com.auto.message.controller;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auto.message.dto.Member;

@RestController
public class ViewController {

	String a;

	@Value("${spring.profiles.active}")
	private String profileActive;
	
	@Value("${custom.title}")
	private String title;
	
	@GetMapping("/title")
	public String title() {
		return title + " - " + profileActive;
	}
	
	private List<String> list;
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String main(String str) {
		str = Objects.toString(str, "null이 들어갈 수 없다.");
		this.a = Objects.requireNonNull(str, "not null");

		return a;
	}
	
	@RequestMapping(value = "/choice/{id}", method = RequestMethod.GET)
	public String callMain (@PathVariable (value="id") String id) {
		
		return "callMain method"+id;
	}
	
	@Cacheable(value = "test")
	@RequestMapping(value = "/filter/main" , method = RequestMethod.GET)
	public String filterMain(String info) {
		try {
			double a = System.currentTimeMillis();
			Thread.sleep(3000);
			double b = System.currentTimeMillis();
			
			System.out.println("출력 : ["+info+"] - "+(b-a)/1000+"초");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return info;

	}
	
	@CacheEvict(value = "test")
	public void createInformation(String info) {
		list.add(info);
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
		
		String name = (String) e.getValue(con);
		
		double b = System.currentTimeMillis();
		
		System.out.println(name + " - 시간 : " +(b-a)/1000 + "초");
		
	}

}
