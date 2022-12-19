package com.auto.message.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Ch1Class {

	public static void main(String[] args) {
		
		ArrayList<String> pitches = new ArrayList<>(Arrays.asList("138","129","142"));
		pitches.sort(Comparator.reverseOrder());
		System.out.println(pitches);
		
		String result = String.join("/", pitches);
		System.out.println(result);
		
		String[] split = result.split("/");
		System.out.println(Arrays.asList(split));
		
		String splitResult = String.join("/", split);
		System.out.println(splitResult);
		
		
		HashSet<Integer> s1 = new HashSet<>(Arrays.asList(1,2,3,4,5,6));
		HashSet<Integer> s2 = new HashSet<>(Arrays.asList(4,5,6,7,8,9));
		
		HashSet<Integer> intersection = new HashSet<>(s1);
		intersection.retainAll(s2);
		System.out.println(intersection);
		
		HashSet<Integer> union = new HashSet<Integer>(s1);
		union.addAll(s2);
		System.out.println(union);
		
		HashSet<Integer> substract = new HashSet<Integer>(s1);
		substract.removeAll(s2);
		System.out.println(substract);
		
		HashSet<String> set = new HashSet<String>();
		set.add("Jump");
		set.addAll(Arrays.asList("To","Java"));
		System.out.println(set);
		
		
		
		
		
		
	}
}
