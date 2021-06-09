package com.auto.message;

public class TestTryMain {

	public static void main(String[] args) {
		
		String a = null;
		try {
			a = "하이";
			if(a.equals("하이")) throw new Exception();
			a = "로하";
		} catch (Exception e) {
			a = "false";
		} finally {
			try {
				if(a.equals("false")) throw new Exception();
			} catch (Exception e2) {
				a = "finally > catch > println";
			} finally {
			}
			System.out.println(a);
		}
	}

}
