package com.auto.message.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class CH1_1_Exercise {
	
	enum CoffeeType {
		AMERICANO,
		ICE_AMERICANO,
		CAFE_LATTE
	};
	
	static void printCoffeePrice(CoffeeType type) {
        HashMap<CoffeeType, Integer> priceMap = new HashMap<>();
        priceMap.put(CoffeeType.AMERICANO, 3000);  // 1: 아메리카노
        priceMap.put(CoffeeType.ICE_AMERICANO, 4000);  // 2: 아이스 아메리카노
        priceMap.put(CoffeeType.CAFE_LATTE, 5000);  // 3: 카페라떼
        int price = priceMap.get(type);
        System.out.println(String.format("가격은 %d원 입니다.", price));
    }
	
	public static void main(String[] args) {

		//[1] 국어 80, 영어 75, 수학 55 평균
		HashSet<Integer> a = new HashSet<>(Arrays.asList(80,75,55));
		int result = 0;
		for (int add : a) {
			result += add;
		}
		result = result/3;
		System.out.println(result);
		
		//[2] 13이 홀수인지 짝수인지
		int num13 = 13;
		if(num13%2 == 0)
			System.out.println("짝수");
		else
			System.out.println("홀수");
		
		//[3] 1. 주민번호 나누기
		String pin = "881120-1068234";
		String front = pin.substring(0, 6);
		String back = pin.substring(7);
		System.out.println(front + "/" + back);
		
		//[4] 2. 주민번호 인덱싱해서 성별 확인
		String s = String.valueOf(pin.charAt(7));
		if("1".equals(s))
			System.out.println("남성");
		else
			System.out.println("여성");
		
		
		//[5] a:b:c:d -> a#b#c#d 변경
		String q5 = "a:b:c:d";
		q5 = q5.replaceAll(":", "#");
		System.out.println(q5);
		
		//[6] [1, 3, 5, 4, 2] 리스트를 [5, 4, 3, 2, 1] 으로 만들기
		ArrayList<Integer> q6 = new ArrayList<Integer>(Arrays.asList(1,3,5,4,2));
		q6.sort(Comparator.reverseOrder());
		System.out.println(q6);
		
		//[7] ['Life', 'is', 'too', 'short'] -> "Life is too short" 문자열로 바꾸기
		ArrayList<String> q7 = new ArrayList<String>(Arrays.asList("Life","is","too","short"));
		
		String strQ7 = String.join(" ", q7);
		System.out.println(strQ7);
		
		//[8] 다음의 맵 grade에서 "B'에 해당되는 값을 추출해 보자. (추출하고 나면 grade에는 "B"에 해당하는 아이템이 사라져야 한다
		HashMap<String, Integer> q8 = new HashMap<String, Integer>();
		q8.put("A", 90);
		q8.put("B", 80);
		q8.put("C", 70);
		System.out.println(q8.remove("B"));
		
		//[9]  중복 숫자를 제거
		ArrayList<Integer> q9 = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5));
		HashSet<Integer> hsQ9 = new HashSet<Integer>(q9);
		System.out.println(hsQ9);
		
		//[10] 다음은 커피의 종류(1: 아메리카노, 2:아이스 아메리카노, 3:카페라떼)를 입력하면 커피의 가격을 알려주는 프로그램이다.
		printCoffeePrice(CoffeeType.AMERICANO);
	}

}
