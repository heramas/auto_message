package com.auto.message;

public class testTtest {

	public static void main(String[] args) {

		int num10 = 77;
		String num2 = Integer.toBinaryString(num10);
		String num8 = Integer.toOctalString(num10);
		String num16 = Integer.toHexString(num10);
		
		System.out.println(">> 2진수 : ["+num2+"] / 8진수 : ["+num8+"] / 16진수 : ["+num16+"] <<");
	}

}
