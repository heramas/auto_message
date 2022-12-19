package com.auto.message;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CalendarExample {

	public static void main(String[] args) {
		
		Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH)+1;
        int date = today.get(Calendar.DATE);
        
        int woy = today.get(Calendar.WEEK_OF_YEAR);
        int wom = today.get(Calendar.WEEK_OF_MONTH);
        
        int doy = today.get(Calendar.DAY_OF_YEAR);
        int dom = today.get(Calendar.DAY_OF_MONTH);
        int dow = today.get(Calendar.DAY_OF_WEEK);
        String dowStr = "";

        switch (dow) {
		case 1: dowStr = "일";
		break;
		case 2: dowStr = "월";
		break;
		case 3: dowStr = "화";
		break;
		case 4: dowStr = "수";
		break;
		case 5: dowStr = "목";
		break;
		case 6: dowStr = "금";
		break;
		case 7: dowStr = "토";
		break;
		}
        
        int hour12 = today.get(Calendar.HOUR);
        int hour24 = today.get(Calendar.HOUR_OF_DAY);
        int minute = today.get(Calendar.MINUTE);
        int second = today.get(Calendar.SECOND);
        
        int milliSecond = today.get(Calendar.MILLISECOND);
        int timeZone = today.get(Calendar.ZONE_OFFSET);
        int lastDate = today.getActualMaximum(Calendar.DATE);
        
        System.out.println("오늘은 " + year +"년 " + month+ "월" + date +"일");
        System.out.println("오늘은 올해의 " + woy +"째주, 이번달의 " + wom + "째주. " + date +"일");
        System.out.println("오늘은 이번 해의 " + doy +"일이자, 이번 달의 " + dom + "일. 요일은 " + dowStr +"요일 (1:일요일)");
        System.out.println("현재 시각은 " + hour12 +":"+ minute + ":"+ second +", 24시간으로 표현하면 " + hour24+":"+ minute + ":"+ second);
        System.out.println("오늘은 " + year +"년 " + month + "월" + date +"일");
        System.out.println("1000분의 1초 (0~999): " + milliSecond);
        System.out.println("timeZone (-12~+12): " + timeZone/(60*60*1000)); // 1000분의 1초를 시간으로 표시하기 위해 60*60*1000
        System.out.println("이 달의 마지막 날: " + lastDate);
        
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.of(12, 20);
        LocalDateTime localDateTime = LocalDateTime.now(); 

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        
        System.out.println("LocalDate.now() :::::::::::::::::::::::::::::::::::::::::::::::: [" + localDate+"]");
        System.out.println("LocalTime.of(12, 20) ::::::::::::::::::::::::::::::::::::::::::: [" + localTime+"]");
        System.out.println("LocalDateTime.now() :::::::::::::::::::::::::::::::::::::::::::: [" + localDateTime+"]");
        System.out.println("OffsetDateTime.now() ::::::::::::::::::::::::::::::::::::::::::: [" + offsetDateTime+"]");
        System.out.println("ZonedDateTime.now(ZoneId.of(\"Europe/Paris\") :::::::::::::::::::: [" + zonedDateTime+"]");
        
        Instant instant = Instant.now();
        Instant instant1 = instant.plus(Duration.ofMillis(5000));
        Instant instant2 = instant.minus(Duration.ofMillis(5000));
        Instant instant3 = instant.minusSeconds(10);
        
        System.out.println("Instant.now() :::::::::::::::::::::::::::::::::::::::::::::::::: ["+instant+"]");
        System.out.println("instant.plus(Duration.ofMillis(5000) ::::::::::::::::::::::::::: ["+instant1+"]");
        System.out.println("instant.minus(Duration.ofMillis(5000) :::::::::::::::::::::::::: ["+instant2+"]");
        System.out.println("instant.minusSeconds(10) ::::::::::::::::::::::::::::::::::::::: ["+instant3+"]");
        
        Duration duration = Duration.ofMillis(5000);
        System.out.println("Duration.ofMillis(5000) :::::::::::::::::::::::::::::::::::::::: [" + duration+"]");
        
        duration = Duration.ofSeconds(60);
        System.out.println("Duration.ofSeconds(60) ::::::::::::::::::::::::::::::::::::::::: [" + duration+"]");
        
        duration = Duration.ofMinutes(10);
        System.out.println("Duration.ofMinutes(10) ::::::::::::::::::::::::::::::::::::::::: [" + duration+"]");
        
        Period period = Period.ofDays(6);
        System.out.println("Period.ofDays(6) ::::::::::::::::::::::::::::::::::::::::::::::: [" + period+"]");
        
        period = Period.ofMonths(6);
        System.out.println("Period.ofMonths(6) ::::::::::::::::::::::::::::::::::::::::::::: [" + period+"]");
        
        period = Period.between(LocalDate.now(), LocalDate.now().plusDays(60));
        System.out.println("Period.between(LocalDate.now(), LocalDate.now().plusDays(60) ::: [" + period+"]");
        
        
		/*
		 * java.util.GregorianCalendar[
		 * 	time=1651803639854,
		 * 	areFieldsSet=true,
		 * 	areAllFieldsSet=true,
		 * 	lenient=true,
		 * 	zone=sun.util.calendar.ZoneInfo[
		 * 		id="Asia/Seoul",
		 * 		offset=32400000,
		 * 		dstSavings=0,
		 * 		useDaylight=false,
		 * 		transitions=30,
		 * 		lastRule=null
		 * 	],
		 * 	firstDayOfWeek=1,
		 * 	minimalDaysInFirstWeek=1,
		 * 	ERA=1,
		 * 	YEAR=2022,
		 * 	MONTH=4,
		 * 	WEEK_OF_YEAR=19,
		 * 	WEEK_OF_MONTH=1,
		 * 	DAY_OF_MONTH=6,
		 * 	DAY_OF_YEAR=126,
		 * 	DAY_OF_WEEK=6,
		 * 	DAY_OF_WEEK_IN_MONTH=1,
		 * 	AM_PM=0,
		 * 	HOUR=11,
		 * 	HOUR_OF_DAY=11,
		 * 	MINUTE=20,
		 * 	SECOND=39,
		 * 	MILLISECOND=854,
		 * 	ZONE_OFFSET=32400000,
		 * 	DST_OFFSET=0
		 * ]

		 */
	}
}
