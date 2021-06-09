package com.auto.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class testMain {
	
	private String 	name;
	private int		score;
	
	public testMain(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	
	public static void main(String[] args) {
		 
//		for (autoRight right : autoRight.values()) {
//			
//			if(right.getRightCheck().equals("N")) {
//				System.out.println("빌링로그만 :: "+right.getRightName());
//			}
//			System.out.println("둘다 뽑자 :: "+right.getRightName());
//		}

		
//		List<String> list = Arrays.asList("김씨","이씨","박씨","최씨");
		
		//========================자바 7 코드 ============================== 경과 시간 : 0.001 
		
//		double st = System.currentTimeMillis();
//		Iterator<String> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			String name = iterator.next();
//			System.out.println(name);
//		}
//		double et = System.currentTimeMillis();
//		System.out.println("경과 : "+(et-st)/1000);
		
		//========================자바 8 코드 ============================== 경과 시간 : 0.019
		
//		double st = System.currentTimeMillis();
//		Stream<String> stream = list.stream();
//		stream.forEach(name -> System.out.println(name));
//		double et = System.currentTimeMillis();
//		System.out.println("경과 : "+(et-st)/1000);
		
		
		//======================= 병렬처리코드 =======================

		double st = System.currentTimeMillis();
		
		List<String> slist = Arrays.asList("김씨","이씨","박씨","최씨","정씨","구씨","낭궁씨","표씨","류씨","장씨","부씨");
		Stream<String> sStream = slist.stream();
		sStream.forEach(testMain::print);
		
		double et = System.currentTimeMillis();
		System.out.println("경과1 : "+(et-st)/1000); // 경과 : 0.021
		
		double st1 = System.currentTimeMillis();
		
		Stream<String> parall = slist.parallelStream();
		parall.forEach(testMain::print);
		
		double et1 = System.currentTimeMillis();
		System.out.println("경과2 : "+(et1-st1)/1000); // 경과 : 0.008
		
		//======================= 중간처리와 최종처리 =======================
		
//		List<testMain> gList = Arrays.asList(new testMain("김씨", 100), new testMain("이씨", 90), new testMain("박씨", 95), new testMain("최씨", 60),
//											 new testMain("부씨", 80),  new testMain("노씨", 70), new testMain("류씨", 75), new testMain("장씨", 30));
//		
//		double st = System.currentTimeMillis();
//		
//		double avg = gList.stream()
//				.mapToInt(testMain::getScore)
//				.average()
//				.getAsDouble();
//		System.out.println("평균 : " + avg);
//		
//		double et = System.currentTimeMillis();
//		System.out.println("경과1 : "+(et-st)/1000); // 경과 : 0.026 / 병렬로 하면 더 느려짐 0.033 - 값이 많으면 모르겠으나 적어서?
		
		String aaa = "2";
		int ab = Integer.getInteger(aaa, 11);
		System.out.println("숫자 : "+ab);
	}
	
	public static void print(String string) {
		System.out.println(string + " : " + Thread.currentThread().getName());
	}

}
