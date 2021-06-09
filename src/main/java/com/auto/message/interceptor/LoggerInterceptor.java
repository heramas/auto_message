package com.auto.message.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoggerInterceptor implements HandlerInterceptor{

	// 컨트롤러가 실행되기 전에 수행할 것을 만들 것이다.
	@Override
	public boolean preHandle(	HttpServletRequest request,
								HttpServletResponse response, 
								Object handler) throws Exception {
		
		log.info("======================== START ========================");
		log.info(" Protocol    : " + request.getProtocol());
		log.info(" Http Method : " + request.getMethod());
		log.info(" Request URL : " + request.getRequestURI());
		log.info(" Remote Addr : " + request.getRemoteAddr());
		log.info(" Remote Host : " + request.getRemoteHost());
		log.info(" Remote Port : " + request.getRemotePort());
		log.info(" Remote User : " + request.getRemoteUser());
		log.info(" Server Name : " + request.getServerName());
		log.info(" Server Port : " + request.getServerPort());
		
		
//		return HandlerInterceptor.super.preHandle(request, response, handler);
		return true;
	}

	// 컨트롤러가 실행 후 수행할 것을 만들 것이다.
	@Override
	public void postHandle(	HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception {
		
		log.info(" Response status : " + response.getStatus());
		log.info("======================== END ========================");
		
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
}
