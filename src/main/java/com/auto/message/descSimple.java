package com.auto.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class descSimple {
	
	public static void main(String[] args) {
		long num = 1529683;
		long temp;
		String tempStr = "";
		long[] answer = Stream.of(String.valueOf(num).split("")).mapToLong(Integer::parseInt).toArray();
		Set<Integer> set = new HashSet<Integer>();

		for (long l : answer) {
			set.add((int) l);
		}
		List<Integer> bb = new ArrayList<Integer>(set);
		Collections.sort(bb, Collections.reverseOrder());
		
		for (int i : bb) {
			int aa = Integer.valueOf(i);
			tempStr = tempStr+""+aa;
			
		}
		temp = Integer.parseInt(tempStr);
		System.out.println(temp);
		
		long 	number 		= 193827645;
		long 	result;
		String 	StrTemp 	= "";
		long[]	setNumber 	= Stream.of(String.valueOf(number).split("")).mapToLong(Integer::parseInt).toArray();
		Set<Integer> setting = new HashSet<Integer>();
		
		
		
			
	}
}
