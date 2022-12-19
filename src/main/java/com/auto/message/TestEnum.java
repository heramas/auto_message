package com.auto.message;

import lombok.Getter;

@Getter
public enum TestEnum {
	APPLE		("사과"		, "중상"	, "중하"	, "중하"	, "중하"	),
	BANANA		("바나나"		, "중"	, "중하"	, "중상"	, "상"	),
	STRAWBERRY	("딸기"		, "상"	, "중하"	, "중하"	, "중하"	),
	MANGO		("망고"		, "상"	, "중하"	, "중하"	, "중하"	),
	MELON		("멜론"		, "중"	, "중하"	, "중하"	, "중하"	),
	PEACH		("복숭아"		, "중상"	, "중하"	, "중하"	, "중하"	),
	WATERMELON	("수박"		, "중"	, "중하"	, "중하"	, "중하"	),
	BLUEBERRY	("블루베리"	, "중"	, "중하"	, "중하"	, "중하"	);
	
	private String name;
	private String brix;
	private String acidity;
	private String flavor;
	private String body;
	
	
	TestEnum(String name, String brix, String acidity, String flavor, String body){
		this.name 		= name;
		this.brix 		= brix;
		this.acidity 	= acidity;
		this.flavor 	= flavor;
		this.body 		= body;
	}
}

