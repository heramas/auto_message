package com.auto.message;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 날짜와 관련된 메소드 
 * 
 * @author : 김정환
 * @version 2.1, 2003/10/14
 *
 * History :
 *
 *		2001.06.30 김정환 : 날짜 연산함수 추가 getDateCalc()
 *		2003.10.14 김정환 : 해당 날짜가  xxxx년 xx월 x번째 주 인지 리턴 getYYYMMWeek()
 *		2003.10.15 김정환 : 해당 날짜의 주에 해당하는 시작일과 종료일 getWeekStartEndDate()
 *		2004.01.12 김정환 : 해당달의 마지막 날을 리턴 getLastDay()
 *
 */
public class DateUtil {
	
	private final static int[] 	SOLAR_MONTH_ARRAY 
							= {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	

	/**
	 * 생성자 
	 */
	public DateUtil() {
	}

    /**
     * 오늘 날짜 리턴 ex) 20000920 .....
     * @param str <br> "day"이면 20000920 <br> "time" 이면 20000920 10:10:10  <br> "time2" 이면 20000920101010 <br> "time3" 이면 MMDD
     * @return 날짜
     */
    public static String getStrToday(String str) {

        Calendar cal = Calendar.getInstance(); //06.26

        if (str.equals("time3")) {
            // MMDD형으로 리턴
            String strDate = null;

            int month = cal.get(Calendar.MONTH) + 1;
            if (month < 10)
                strDate = "0" + String.valueOf(month);
            else
                strDate = String.valueOf(month);

            int day = cal.get(Calendar.DATE) ;
            if (day < 10)
                strDate += "0" + String.valueOf(day);
            else
                strDate += String.valueOf(day);

            return strDate;
        }

        String strDate = String.valueOf(cal.get(Calendar.YEAR));

        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10)
            strDate += "0" + String.valueOf(month);
        else
            strDate += String.valueOf(month);

        int day = cal.get(Calendar.DATE) ;
        if (day < 10)
            strDate += "0" + String.valueOf(day);
        else
            strDate += String.valueOf(day);

        if (str.equals("day"))
            return strDate;

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            if ( !str.equals("time2"))
                strDate += " ";
            strDate += "0" + String.valueOf(hour);
        } else {
            if ( !str.equals("time2"))
                strDate += " ";
            strDate += String.valueOf(hour);
        }

        int minute = cal.get(Calendar.MINUTE);
        if (minute < 10) {
            if ( !str.equals("time2"))
                strDate += ":";
            strDate += "0" + String.valueOf(minute);
        } else {
            if ( !str.equals("time2"))
                strDate += ":";
            strDate += String.valueOf(minute);
        }

        int second = cal.get(Calendar.SECOND) ;
        if (second < 10) {
            if ( !str.equals("time2"))
                strDate += ":";
            strDate += "0" + String.valueOf(second);
        } else {
            if ( !str.equals("time2"))
                strDate += ":";
            strDate += String.valueOf(second);
        }

        return strDate;
    }

    /**
     * 오늘의 요일 
	 *
	 * 리턴값 : <br>
	 *      1 : 일요일<br>
	 *      2 : 월요일<br>
	 *      3 : 화요일<br>
	 *      4 : 수요일<br>
	 *      5 : 목요일<br>
	 *      6 : 금요일<br>
	 *      7 : 토요일<br>
	 *
     * @return 요일  
     */
    public static int getDayOfWeek() {
		return getDayOfWeek( getStrToday("day")) ;
	}

    /**
     * 지정한 날짜의 요일 
	 *
	 * 리턴값 : <br> 
	 *      1 : 일요일<br>
	 *      2 : 월요일<br>
	 *      3 : 화요일<br>
	 *      4 : 수요일<br>
	 *      5 : 목요일<br>
	 *      6 : 금요일<br>
	 *      7 : 토요일<br>
	 *
     * @param strDate 날짜 ex) 20001013
     * @return 요일  
     */
    public static int getDayOfWeek(String strDate) {

		int year = Integer.valueOf(strDate.substring(0,4)).intValue();
	    int month = Integer.valueOf(strDate.substring(4,6)).intValue();
		month--;	// 0 : 1월 11 : 12월....
	    int date = Integer.valueOf(strDate.substring(6,8)).intValue();
	        
	    Calendar cal = Calendar.getInstance();
	        
	    cal.set(year,month,date);
	        
	    return cal.get(Calendar.DAY_OF_WEEK);

	}

	/**
	 * 날짜 연산함수  : 2001.06.30 김정환 추가
 	 *
	 * ex)  getDateCalc("DAY", "20010630", -1) 하면 리턴값은 "20010629" 
	 *   	getDateCalc("MONTH", "20010630", -1) 하면 리턴값은 "20010530" 
	 *   	getDateCalc("YEAR", "20010630", -1) 하면 리턴값은 "20000630" 
	 *	 
	 * @param strField 연산할 필드  "YEAR", "MONTH", "DAY"
	 * @param strDate 날짜 ex) 20010630
	 * @param amount  	   ex) -1, 2 
	 * @return 계산된 날짜 
	 */
	public static String getDateCalc(String strField, String strDate, int amount) {
		int year = Integer.valueOf(strDate.substring(0,4)).intValue();
        int month = Integer.valueOf(strDate.substring(4,6)).intValue();
		month--;
        int day = Integer.valueOf(strDate.substring(6,8)).intValue();

        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day);	

		if ( strField.equals("YEAR"))
			cal.add(Calendar.YEAR, amount);
		else if ( strField.equals("MONTH"))
			cal.add(Calendar.MONTH, amount);
		else 
			cal.add(Calendar.DATE, amount);

        String strRetDate = String.valueOf(cal.get(Calendar.YEAR));

        month = cal.get(Calendar.MONTH) + 1;
        if (month < 10)
            strRetDate += "0" + String.valueOf(month);
        else
            strRetDate += String.valueOf(month);

        day = cal.get(Calendar.DATE) ;
        if (day < 10)
            strRetDate += "0" + String.valueOf(day);
        else
            strRetDate += String.valueOf(day);

        return strRetDate;

	}

// mail service에쓰는 최종 확인 일시 return value ex) 2001/06/05 07:30 
//2001.06.05 엄윤호
	public synchronized static String getMailDate() {

		StringBuffer sb = new StringBuffer();
        	
        	Calendar cal = Calendar.getInstance();

	// 년
		sb.append(String.valueOf(cal.get(Calendar.YEAR))+"/");	
         // 월
         int month = cal.get(Calendar.MONTH) + 1;
         if (month < 10)
                	sb.append("0").append(String.valueOf(month)).append("/");
    	else
                	sb.append(String.valueOf(month)).append("/");
	// 일
        	int day = cal.get(Calendar.DATE) ;
         if (day < 10)
          	sb.append("0").append(String.valueOf(day)).append(" ");
        	else
                	sb.append(String.valueOf(day)).append(" ");
	// 시
        	int hour = cal.get(Calendar.HOUR_OF_DAY);
        	if (hour < 10) {
            	sb.append("0").append(String.valueOf(hour));
        	} else {
            	sb.append(String.valueOf(hour));
        	}
	// 분
        	int minute = cal.get(Calendar.MINUTE);
        	if (minute < 10) {
               	sb.append(":0").append(String.valueOf(minute));
        	} else {
                	sb.append(":").append(String.valueOf(minute));
        	}

       	return sb.toString();
    }
    
    /**
     * 지정한 날짜가 그 달의 몇번째 주 인지..
	 *
	 * 리턴값 : <br> 
	 *      1 : 1번째 주<br>
	 *      2 : 2번째 주<br>
	 *      3 : 3번째 주<br>
	 *      4 : 4번째 주<br>
	 *      5 : 5번째 주<br>
	 *
     * @param yyyymmdd 날짜 ex) 20031014
     * @return 주  
     */    
	public static String getYYYMMWeek(String yyyymmdd) {
	
		String strRet = "";
		String strDate;
		int dayOfWeekFirstday;	// 달의 첫번째 날의 요일
		int dayTemp;
		int day;
		
		strDate  = yyyymmdd.substring(0,4);
		strDate += yyyymmdd.substring(4,6);
		strDate += "01";
		
		day = Integer.valueOf(yyyymmdd.substring(6,8)).intValue();
		
		dayOfWeekFirstday = getDayOfWeek(strDate);
		
		dayTemp = 1 + (7 - dayOfWeekFirstday);
		
		for(int i=0; i<5; i++) {
			
			if(day <= dayTemp) {
				strRet = String.valueOf(i + 1);
				break;
			}
			
			dayTemp += 7;
		}
		
		return strRet;
	}

	/**
	 * 지정한 날짜의 주에 해당하는 시작일과 종료일
	 *
	 * ex) 20031015
	 *		리턴 : 2003101220031018
	 *
     * @param 	yyyymmdd 날짜 ex) 20031015
     * @return 	지정한 날짜의 주에 해당하는 시작일과 종료일 (yyyymmddyyyymmdd) ex) 2003101220031018
     */
	public static String getWeekStartEndDate(String yyyymmdd) {
		
		String strRet;
		String strStartDate;
		String strEndDate;
		int dayOfWeek;
		
		dayOfWeek = getDayOfWeek(yyyymmdd);
		
		strStartDate 	= getDateCalc("DAY", yyyymmdd, -(dayOfWeek - 1));
		strEndDate		= getDateCalc("DAY", yyyymmdd, +(7 - dayOfWeek));
		
		strRet = strStartDate + strEndDate;
		
		return strRet;
	}
	
	/**
	 * getLastDay
	 * @param String YYYYMM
	 * @return String 28, 29, 30, 31
	 */
	public static String getLastDay(String dt) {
		
		int 	lastday;
		
		if ( dt.length() < 6 ) {
			return "";
		}
		
		int year = Integer.parseInt( dt.substring(0,4) );
		int month = Integer.parseInt( dt.substring(4,6) );
		
		if ( isLeapYear(year) )
			lastday =  ( month == 2 ) ? 29 : SOLAR_MONTH_ARRAY[ month-1 ];
		else
			lastday = SOLAR_MONTH_ARRAY[ month-1 ];
			
		return String.valueOf(lastday);
	}	
	
	/**
	 * isLeapYear
	 * @param year yyyy
	 * @return true leap year, false non-leap year
	 */
	public static boolean isLeapYear(int year) {
		return (year%400==0 || year%100!=0 && year%4==0);
	}
	
	/**
	 * 지정한 날짜의 주에 해당하는 시작일과 종료일
	 *
	 * ex) 20031015
	 *		리턴 : 2003101220031018
	 *
     * @param 	yyyymmdd 날짜 ex) 20031015
     * @param	idx			시작일 인덱스 (0 : 일요일, 1 : 월요일 ...)
     * @return 	지정한 날짜의 주에 해당하는 시작일과 종료일 (yyyymmddyyyymmdd) ex) 2003101220031018
     */
     // 2004.11.14 추가 임현호
	public static String getWeekStartEndDate1(String yyyymmdd, int idx) {
		
		String strRet;
		String strStartDate;
		String strEndDate;
		int dayOfWeek;
		
		dayOfWeek = getDayOfWeek(yyyymmdd);
		dayOfWeek = dayOfWeek - idx;
		if (dayOfWeek <= 0){
			dayOfWeek = 7 - dayOfWeek;	
		}
		
		strStartDate 	= getDateCalc("DAY", yyyymmdd, -(dayOfWeek - 1));
		strEndDate		= getDateCalc("DAY", yyyymmdd, +(7 - dayOfWeek));
		
		strRet = strStartDate + strEndDate;
		
		return strRet;
	}
	
	
	

	 /**
	  * 
	  *  yyyyMMdd Format으로 입력된 날짜에 addDay 만큼 더한 날짜(yyyyMMdd)를 Return한다.
	  * 
	  * <pre>
	  * 
	  * [사용 예제]
	  * 
	  * addDays("20040225", 1) ===> 20040226
	  * 
	  * </pre>
	  * 
	  * @param date 
	  * @param addDay
	  * @return String
	  * @throws ChainedException
	  */
	 public static String addDays(String date, int addDay) throws Exception {
	  return addDays(date, addDay, "yyyyMMdd");
	 }
	 
	 /**
	  * 
	  * 입력한 날짜를 입력한 Format으로 해석하여 addDay 만큼 더한 날짜를 Return한다.
	  * 
	  * addDays("2004-02-25", 1, "yyyy-MM-dd") ===> 2004-02-26
	  * 
	  * @param date 
	  * @param addDay 더할 일수
	  * @param format 
	  * @return String
	  * @throws ChainedException
	  */
	 public static String addDays(String date, int addDay, String format) throws Exception {
	  java.text.SimpleDateFormat formatter = 
	   new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
	  java.util.Date formattedDate = ConvertUtil.formatValidDate(date, format);
	  formattedDate.setTime(formattedDate.getTime() + ((long) addDay * 1000 * 60 * 60 * 24));
	  return formatter.format(formattedDate);
	 }
	 
	 /**
	  * 
	  *  yyyyMMdd Format으로 입력된 날짜에 addMonth 만큼 더한 날짜를 Return한다.
	  * 
	  * <pre>
	  * 
	  * [사용 예제]
	  * 
	  * addMonths("20040225", 1) ===> 20040325
	  * 
	  * </pre>
	  * 
	  * @param date 
	  * @param addMonth 더할 월수 
	  * @return String
	  * @throws ChainedException
	  */
	 public static String addMonths(String date, int addMonth) throws Exception {
	  return addMonths(date, addMonth, "yyyyMMdd");
	 }
	 
	 /**
	  * 
	  * 입력한 날짜를 입력한 Format으로 해석하여 addMonth 만큼 더한 날짜를 Return한다.
	  * 
	  * addMonths("2004-02-25", 1, "yyyy-MM-dd") ===> 2004-03-25
	  * 
	  * @param date 
	  * @param addMonth 더할 월수
	  * @param format 
	  * @return String
	  * @throws ChainedException
	  */
	 public static String addMonths(String date, int addMonth, String format) throws Exception {
	  java.text.SimpleDateFormat formatter =
	   new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
	  java.util.Date formattedDate = ConvertUtil.formatValidDate(date, format);
	  
	  java.text.SimpleDateFormat yearFormat =
	   new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
	  java.text.SimpleDateFormat monthFormat =
	   new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
	  java.text.SimpleDateFormat dayFormat =
	   new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);
	   
	  int year = Integer.parseInt(yearFormat.format(formattedDate));
	  int month = Integer.parseInt(monthFormat.format(formattedDate));
	  int day = Integer.parseInt(dayFormat.format(formattedDate));
	  month += addMonth;
	  if (addMonth > 0) {
	   while (month > 12) {
	    month -= 12;
	    year += 1;
	   }
	  } else {
	   while (month <= 0) {
	    month += 12;
	    year -= 1;
	   }
	  }
	  java.text.DecimalFormat fourDf =
	   new java.text.DecimalFormat("0000");
	  java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
	  String tempDate =
	   String.valueOf(fourDf.format(year))
	    + String.valueOf(twoDf.format(month))
	    + String.valueOf(twoDf.format(day));
	  java.util.Date targetDate = null;
	  try {
	   targetDate = ConvertUtil.formatValidDate(tempDate, "yyyyMMdd");
	  } catch (Exception e) {
	   day = getLastDay(year, month);
	   tempDate =
	    String.valueOf(fourDf.format(year))
	     + String.valueOf(twoDf.format(month))
	     + String.valueOf(twoDf.format(day));
	   targetDate = ConvertUtil.formatValidDate(tempDate, "yyyyMMdd");
	  }
	   return formatter.format(targetDate);
	 }
	 
	 /**
	  * 
	  *  yyyyMMdd Format으로 입력된 날짜에 addYear 만큼 더한 날짜를 Return한다.
	  * 
	  * <pre>
	  * 
	  * [사용 예제]
	  * 
	  * addYears("20040225", 1) ===> 20050225
	  * 
	  * </pre>
	  * 
	  * @param date 
	  * @param addYear 더할 년수 
	  * @return String
	  * @throws ChainedException
	  */
	 public static String addYears(String date, int addYear) throws Exception {
	  return addYears(date, addYear, "yyyyMMdd");
	 }
	 
	 /**
	  * 
	  * 입력한 날짜를 입력한 Format으로 해석하여 addYear 만큼 더한 날짜를 Return한다.
	  * 
	  * addYears("2004-02-25", 1, "yyyy-MM-dd") ===> 2005-02-25
	  * 
	  * @param date 
	  * @param addYear 더할 년수 
	  * @param format 
	  * @return String
	  * @throws ChainedException
	  */
	 public static String addYears(String date, int addYear, String format) throws Exception { 
	  
	  java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
	  java.util.Date formattedDate = ConvertUtil.formatValidDate(date, format);

	  Calendar calendar = new  GregorianCalendar();
	  calendar.setTime(formattedDate);      
	  calendar.add(Calendar.YEAR, addYear);
	  formattedDate = calendar.getTime();
	  
	  return formatter.format(formattedDate);

	 }


	 /**
	  * 
	  * yyyyMMdd Format으로 입력된 날짜에서 해당 년월의 마지막 날짜를 Return한다.
	  * 
	  * <pre>
	  * 
	  * [사용 예제]
	  * 
	  * lastDayOfMonth("20040202") ===> 20040229
	  * 
	  * </pre>
	  * 
	  * @param date
	  * @return String
	  * @throws ChainedException
	  */ 
	 public static String getLastDayOfMonth(String date) throws Exception {
	  return getLastDayOfMonth(date, "yyyyMMdd");
	 }

	 /**
	  * 
	  * 입력된 날짜을 입력된 Format 으로 해석하여 그 날짜에서 해당 년월의 마지막 날짜를 Return한다.
	  * 
	  * <pre>
	  * 
	  * [사용 예제]
	  * 
	  * lastDayOfMonth("20040202", "yyyyMMdd") ===> 20040429
	  * 
	  * </pre>
	  * 
	  * @param date 
	  * @param format 
	  * @return String
	  * @throws ChainedException
	  */ 
	 public static String getLastDayOfMonth(String date, String format) throws Exception {
	  java.text.SimpleDateFormat formatter =
	   new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
	  java.util.Date formattedDate = ConvertUtil.formatValidDate(date, format);
	  java.text.SimpleDateFormat yearFormat =
	   new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
	  java.text.SimpleDateFormat monthFormat =
	   new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
	  int year = Integer.parseInt(yearFormat.format(formattedDate));
	  int month = Integer.parseInt(monthFormat.format(formattedDate));
	  int day = getLastDay(year, month);
	  java.text.DecimalFormat fourDf =
	   new java.text.DecimalFormat("0000");
	  java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
	  String tempDate =
	   String.valueOf(fourDf.format(year))
	    + String.valueOf(twoDf.format(month))
	    + String.valueOf(twoDf.format(day));
	  java.util.Date targetDate = ConvertUtil.formatValidDate(tempDate, "yyyyMMdd");
	  return formatter.format(targetDate);
	 }

	 /**
	  * 
	  * 입력된 년도와 입력된 월의 마지막 일를 Return한다.
	  * 
	  * <pre>
	  * 
	  * [사용 예제]
	  * 
	  * lastDay(2004,02) ===> 29
	  * 
	  * </pre>
	  * 
	  * @param year 
	  * @param month 
	  * @return int
	  * @throws ParseException
	  */ 
	 public static int getLastDay(int year, int month) throws java.text.ParseException {
	  int day = 0;
	  switch (month) {
	   case 1 :
	   case 3 :
	   case 5 :
	   case 7 :
	   case 8 :
	   case 10 :
	   case 12 :
	    day = 31;
	    break;
	   case 2 :
	    if ((year % 4) == 0) {
	     if ((year % 100) == 0 && (year % 400) != 0) {
	      day = 28;
	     } else {
	      day = 29;
	     }
	    } else {
	     day = 28;
	    }
	    break;
	   default :
	    day = 30;
	  }
	  return day;
	 }

	 /**
	  * 
	  * 입력된 시간을 입력된 Format 으로 해석하여 수행시간을 milliseconds 로 Return한다.
	  * 
	  * <pre>
	  * 
	  * [사용 예제]
	  * 
	  * getElapsedTime("111111","121111","HHmmss") ===> 3600000
	  * 
	  * </pre>
	  * 
	  * @param start 
	  * @param end
	  * @param format : default format = HHmmss
	  * @return long : milliseconds
	  * @throws ChainedException
	  */ 
	 public static long getElapsedTime(String start, String end, String format) throws Exception {
	  if (format == null || format.length() == 0) {
	   format = "HHmmss";
	  }

	  Date startTime = ConvertUtil.formatValidDate(start, format);
	  Date endTime  = ConvertUtil.formatValidDate(end, format);
	  
	  return endTime.getTime() - startTime.getTime();
	 }
	 
	 /**
	  * 
	  * 현재 시간을 Long Type 으로 Return한다. (워크플로우 작업이력저장 용도)
	  * 
	  * <pre>
	  * 
	  * [사용 예제]
	  * 
	  * DateUtil.getCurrentTime()
	  * 
	  * </pre>
	  * @return Long
	  * @throws Exception
	  */ 
	 public static Long getCurrentTime() throws Exception {
	  java.util.Date date = new java.util.Date(); 

	  long ltime1 = date.getTime();   //현재시간

	  Calendar cal=Calendar.getInstance();
	  cal.set(1970,0,1,9,0,0);//파일넷 기준시간

	  long ltime2 = cal.getTimeInMillis();

	  long curTime = ltime1 - ltime2;

	  return new Long(curTime / 1000);
	 }


	public static String getCurrentYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		//get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, 1);  // number of days to add

		//System.out.println(dateFormat.format(cal.getTime()));
		String year = dateFormat.format(cal.getTime()).substring(0,4);
		//System.out.println(year);
		return year;
	}
}

