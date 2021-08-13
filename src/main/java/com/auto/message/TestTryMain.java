package com.auto.message;

public class TestTryMain {

	public static void main(String[] args) throws Exception {
		
		String a = null;
		try {
			a = "하이";
			if(a.equals("하이")) throw new ResponseException("99");
			a = "로하";
			
		} catch (ResponseException e) {
			if("99".equals(e.getErrCode())) {
				a = "돌려";
			}
			if("10".equals(e.getErrCode())) {
				 a = "false";
			}
		} catch (NumberFormatException e) { 

		} catch (Exception e) {
			
			if("하이".equals(a)) { 
				a = "돌려";
			}
			
			if("돌려".equals(a)) a = "false";
		}finally {
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
