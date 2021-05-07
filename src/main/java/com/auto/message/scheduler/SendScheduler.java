package com.auto.message.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendScheduler {
	
	
	@Scheduled(cron = "0 0 * * * ?")
	public void sendCron() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date now = new Date();
		String sDate = dateFormat.format(now);
		System.out.println("rest : " + sDate);
	}
	
	@Scheduled(fixedDelay = 60000)
	public void oneMinute() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date now = new Date();
		String sDate = dateFormat.format(now);
		System.out.println("work : " + sDate);
	}
}
