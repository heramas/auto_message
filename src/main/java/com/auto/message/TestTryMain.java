package com.auto.message;

public class TestTryMain {

	public static void main(String[] args) throws Exception {
		
		String a = null;
		try {
			a = "ownReplace";
			if(a.equals("ownReplace")) throw new ResponseException("99");
			a = "twoReplace";
			
		} catch (ResponseException e) {
			if("99".equals(e.getErrCode())) {
				a = "threeReplace";
			}
			if("10".equals(e.getErrCode())) {
				 a = "fourReplace";
			}
		} catch (NumberFormatException e) { 

		} catch (Exception e) {
			
			if("ownReplace".equals(a)) { 
				a = "threeReplace";
			}
			
			if("threeReplace".equals(a)) a = "fourReplace";
		}finally {
			try {
				if(a.equals("fourReplace")) throw new Exception();
			} catch (Exception e2) {
				a = "finally > catch > println";
			} finally {
			}
			System.out.println(a);
		}
	}

}
