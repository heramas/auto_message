package com.auto.message;

public class imageTestEncording {

	public static void main(String[] args) {
		String str = "[��濡���.] [[ �⑺�� 2010 媛����� ]] 1080P REMUX FHD 釉�猷⑤���� - The Yellow Sea.2010";
		String[] charStr = {"utf-8","euc-kr","ksc5601","iso-8859-1","x-windows-949"};
		
		for (int i = 0; i < charStr.length; i++) {
			for (int j = 0; j < charStr.length; j++) {
				try {
					System.out.println("["+charStr[i] +", "+ charStr[j] + "] = " + new String(str.getBytes(charStr[i]), charStr[j]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
