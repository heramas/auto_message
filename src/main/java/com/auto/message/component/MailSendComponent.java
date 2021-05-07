package com.auto.message.component;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

interface MailSendUtil {
	void sendMail(String toAddr, String subject, String body);
}

@Log4j2
@Component
public class MailSendComponent implements MailSendUtil {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(String toAddr, String subject, String body) {
		
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
		
		try {
			msgHelper.setTo(toAddr);
			msgHelper.setSubject(subject);
			msgHelper.setText(body);
		} catch (Exception e) {
			log.info("{}",e.toString());
		}
		
		mailSender.send(msg);
	}

	
}
