package com.auto.message;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Fruit {
	APPLE		("사과"		,1500	),
	BANANA		("바나나"		,800	),
	MELON		("멜론"		,4000	),
	MANGO		("망고"		,3000	),
	BEACH		("복숭아"		,2000	),
	WATERMELON	("수박"		,9000	),
	STRAWBERRY	("딸기"		,200	),
	BLUEBERRY	("블루베리"	,100	);
	
	private final String label;
	private final int price;
	
	Fruit(String label, int price){
		this.label	= label;
		this.price	= price; 
	}
	
	public String label() {
		return label;
	}
    public int price() {
        return price;
    }
	
	private static final Map<String, Fruit> BY_NAME = 
			Stream.of(values()).collect(Collectors.toMap(Fruit::label	, Function.identity()));
	
	private static final Map<Integer,Fruit> BY_PRICE = 
			 Stream.of(values()).collect(Collectors.toMap(Fruit::price	, Function.identity()));
	
	public static Fruit valueOfLabel(String label) {
		return BY_NAME.get(label);
	}
	
	public static Fruit valueOfPrice(int price) {
		return BY_PRICE.get(price);
	}
}
