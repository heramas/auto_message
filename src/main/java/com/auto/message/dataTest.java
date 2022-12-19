package com.auto.message;

import java.text.SimpleDateFormat;

public class dataTest {

	public static void main(String[] args) {
			
		java.util.Date currTime = new java.util.Date();
		String reg_date = (new SimpleDateFormat("yyyyMMdd")).format(currTime);
		String ver 		= reg_date.substring(2, 8);
		
		String aa = "1235.mp4.pth";
		aa = aa.replaceAll(".pth", "");
		
		System.out.println(ver);
		System.out.println();
		
		System.out.println(Fruit.valueOfPrice(100));
		
		String osp = "AFREECATV";
		String code = "19";
		String result = "1";
		
		if("AFREECATV".equals(osp) &&  ("19".equals(code) || "15".equals(code)) ) {
			result = "2";
		}
		
		System.out.println(result);
	}

}
