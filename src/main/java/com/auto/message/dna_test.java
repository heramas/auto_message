package com.auto.message;

import java.util.Calendar;

public class dna_test {

	public static void main(String[] args) {
		
		String[] dna = { "0b4e9f5695f11ac1528c9ceabacff882",
						 "135a424ee4e3dbab40dfe989c8a96d25",
						 "298f6cb891fb6e155fd868b28777d446",
						 "3fa58f017f6de95e5bb6b27960675320",
						 "49ed365695b7ddf224c7b14b7bf13636",
						 "5b103e93ac84732839d52308e7948ee1",
						 "62fe5fcdf4b488f6898e2a3fbb801137",
						 "707670769ff7c0103f1a33669b0b27cb",
						 "8450ff69f75afca09ae9999954e46251",
						 "96f0169ef2ecdcb6bc06466aa464a2eb",
						 "a27508d7ff189cea736eb3a8272d5e29",
						 "b5d092dfc2775cb672e6e12e4477659e",
						 "c63dde6c9415ce4aef3780aa78d6e587",
						 "d19ed0786a201661139f6341fc74ca0c",
						 "e31f13b503b1d0e400f5611dcbdf7a41",
						 "f2b0d8b023b6ecd07e4c9787f4c14423"};
		
		
		Calendar today = Calendar.getInstance();
		int dow = today.get(Calendar.DAY_OF_WEEK);
		System.out.println(dow);
		for (String str : dna) {
			String subStr = str.substring(0,1);
			
			if( dow == 1  && ( "0".equals(subStr) || "1".equals(subStr) || "2".equals(subStr))) {
				System.out.println(str);
			}else if ( dow == 2 && ("2".equals(subStr) || "3".equals(subStr) || "4".equals(subStr)) ) {
				System.out.println(str);
			}else if  (dow == 3 && ("4".equals(subStr) || "5".equals(subStr) || "6".equals(subStr)) ) {
				System.out.println(str);
			}else if ( dow == 4 && ("6".equals(subStr) || "7".equals(subStr) || "8".equals(subStr)) ) {
				System.out.println(str);
			}else if ( dow == 5 && ("8".equals(subStr) || "9".equals(subStr) || "a".equals(subStr)) ) {
				System.out.println(str);
			}else if ( dow == 6 && ("a".equals(subStr) || "b".equals(subStr) || "c".equals(subStr)) ) {
				System.out.println(str);
			}else if ( dow == 7 && ("d".equals(subStr) || "e".equals(subStr) || "f".equals(subStr)) ) {
				System.out.println(str);
			}
		}

	}

}
