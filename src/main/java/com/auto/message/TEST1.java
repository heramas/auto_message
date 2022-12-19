package com.auto.message;

import java.util.ArrayList;
import java.util.List;

public class TEST1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> ospList = new ArrayList<String>();
		ospList.add("OOTTX");
		
		boolean result = false;
		
		result = ospList.contains("OOTTX");
		
		System.out.println(result);
	}

}
