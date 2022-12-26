package com.auto.message.java;

public class CH2_1_Exercise {

	public static void main(String[] args) {
		int a = 0;
		int sum = 0;
		while(a < 1000 ) {
			int temp;
			a++;
			temp = 3*a;
			if(temp%3 == 1)
				continue;
			else {
				sum += temp;
			}
			System.out.println( "[" + a + "] - 3의 배수 : [" + temp + "] 값이 합하면 [" + sum + "]" );
		}
		System.out.println(sum);
		
		
		String star = "";
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 1; j++) {
				star += "*";
				System.out.println(star);
			}
		}
		
		int temp = 0;
		int[] marks = {70, 60, 55, 75, 95, 90, 80, 80, 85, 100};
		for (int i : marks) {
			temp += i;
		}
		temp = temp/marks.length;
		System.out.println(temp);
	}
}
