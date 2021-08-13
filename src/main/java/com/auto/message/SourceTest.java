package com.auto.message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SourceTest {

	public static void main(String[] args) throws ParseException {
		String ret_ch_content_id = "iMBC_00108100398";
//		String ret_ch_content_id = "EMPTY";
//		String ret_ch_content_id = null;
//		String ret_ch_id = "2615595";
		String ret_ch_id = null;
//		String ret_price = "1000";
		String ret_price = null;
//		String ret_price_streaming = "1000";
		String ret_price_streaming = null;
		String setRight_con_id = "";
		//----------- 06.18 추가 -----------
	
		Date getToday = new Date();
		Date compDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String gStr = sdf.format(getToday);
		
		getToday	= sdf.parse(gStr);
		compDate	= sdf.parse("20210616000000");
	  	
		int com = compDate.compareTo(getToday);
		
		if(com > 0){
			//out.println("2021년06월18일 00시부터 돌아야한다.");
			// 20210618000000 같거나 이후 시간부터 가격 변동.
		}else{
			List<String> mbc 		= Arrays.asList("iMBC_001","iMBC_011");
			List<String> mbc_price 	= Arrays.asList("700","1000","1500","3000");
			
			List<String> sbs_price 	= Arrays.asList("1000","1300","1500");
		    
			ret_ch_content_id = ret_ch_content_id == null ? "" : ret_ch_content_id;
			// MBC
			if( "2615595".equals(ret_ch_id) && ! "".equals(ret_ch_content_id) && ret_ch_content_id.length() > 8 ) 
				setRight_con_id = ret_ch_content_id.substring(0, 8);
		    if( mbc.contains(setRight_con_id) ) {
		    	if( mbc_price.contains(ret_price) ) {
		        	ret_price = "2000";
		    		ret_price_streaming = "2000";
		    	}
		    }
		    
		    // SBS
		    if( "2615596".equals(ret_ch_id) ) {
		    	if( sbs_price.contains(ret_price) ) {
		    		ret_price = "2000";
		    		ret_price_streaming = "2000";
		    	}
		    }
		}
	   //----------- 06.18 추가 -----------
		
        System.out.println(ret_ch_id + ", " +setRight_con_id + ", " + ret_price + ", " + ret_price_streaming);
	}
}
