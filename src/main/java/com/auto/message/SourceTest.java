package com.auto.message;

import java.util.Arrays;
import java.util.List;

public class SourceTest {

	public static void main(String[] args) {
		
		String ret_ch_content_id = "iMBC_01100000";
		String ret_ch_id = "2615595";
		String ret_price = "700";
		String ret_price_streaming = "700";
		//----------- 06.07 추가 -----------
		List<String> mbc = Arrays.asList("iMBC_001","iMBC_011");
		List<String> mbc_price = Arrays.asList("700","1000","1500","3000");

		String setRight_con_id = "";
        if("2615595".equals(ret_ch_id)) setRight_con_id = ret_ch_content_id.substring(0, 8);
        if(mbc.contains(setRight_con_id)) {
        	if( mbc_price.contains(ret_price) ) {
            	ret_price = "2000";
        		ret_price_streaming = "2000";
        	}
        }// mbc end
        
        if( "2615557".equals(ret_ch_id) || "2615596".equals(ret_ch_id) ) { // kbs , sbs
        	if("1200".equals(ret_price) || "1500".equals(ret_price) && ret_price == ret_price_streaming) {
        		ret_price = "2000";
        		ret_price_streaming = "2000";
        	}
        }
       //----------- 06.07 추가 -----------
        System.out.println(ret_ch_id + ", " +setRight_con_id + ", " + ret_price + ", " + ret_price_streaming);
	}
}
