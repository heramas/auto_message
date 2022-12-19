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
	}

}
