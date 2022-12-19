package com.auto.message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class returnExample {

	public enum osp{
		
		FILESUN	("FILESUN"		,"244786EFE0FD57E7"),
		FILEJO	("FILEJO"		,"F2A941ED20607FA5"),
		GAMPLE	("GAMPLE"		,"5D182136DA7DA7F7"),
		MEGAFILE("MEGAFILE"		,"D3011BE0349B4A77"),
		CLUBNEX	("CLUBNEX"		,"3AB5D7E5D88C6408"),
		FILEBOGO("FILEBOGO"		,"1A866A4BDFA0559B"),
		ONDISK	("ONDISK"		,"74F1881D8BEF12C5"),
		KDISK	("KDISK"		,"ACB091B1336EFA5A"),
		PDPOP	("PDPOP"		,"87E6C36E1B0C279E"),
		TODISK	("TODISK"		,"0EE5DBE007161FD7"),
		TOTODISK("TOTODISK"		,"E1BC1EE7268077AB"),
		OOTTX	("OOTTX"		,"4565B15128022900"),
		FILECAST("FILECAST"		,"C12DB214FF8C9A9B"),
		;
		
		private String osp_id;
		private String serviceKey;
		
		private osp(String osp_id, String serviceKey) {
			this.osp_id = osp_id;
			this.serviceKey = serviceKey;
		}
		
	}
	
	private static String AddDate(String strDate, int year, int month, int day) throws Exception {
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
		
		Calendar cal = Calendar.getInstance();
		
		Date dt = dtFormat.parse(strDate);
		
		cal.setTime(dt); 
		
		cal.add(Calendar.YEAR, year); 
		cal.add(Calendar.MONTH, month); 
		cal.add(Calendar.DATE, day); 
		
		return dtFormat.format(cal.getTime()); 
	}


	public static void main(String[] args) throws Exception {
		
		String ospNm = "PDPOP";
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");

		java.util.Date currTime = new java.util.Date();
		String reg_date = (new SimpleDateFormat("yyyyMMdd")).format(currTime);
		String hour  = (new SimpleDateFormat("HH")).format(currTime);
		
		System.out.println(hour);
		
		String year 	= reg_date.substring(0,4);
		String today 	= reg_date.substring(0,8);
		String nextDay	= "";
		
		Calendar cal = Calendar.getInstance();
		Date dt = dtFormat.parse(today);
		cal.setTime(dt);
		cal.add(Calendar.DATE, 1);
		
		
		
		nextDay = dtFormat.format(cal.getTime());
		
		System.out.println("hey : "+today+hour+"0000");
		System.out.println("hey : "+nextDay+"000000");
		
		String addDay = AddDate("20220430", 0, 0, 1);
		System.out.println(addDay);
		
	
		for (osp getOsp : osp.values()) {
			System.out.println(getOsp.name());
			if(getOsp.name().equals(ospNm)) {
				System.out.println(getOsp.serviceKey);
				break;
			}
		
		}
		
	}

}
