package com.auto.message;

public class tst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String param_u_hash =  "c6bc43a97496e54dfc65cd4765d3f75f";
		
		String tempHash = param_u_hash.replaceAll("[^a-zA-Z]", "");
		
		System.out.println(tempHash.length());
		System.out.println(tempHash);
		
		
	}

}
