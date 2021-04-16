package com.auto.message.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	// 컨트롤러가 실행되기 전에 수행할 것을 만들 것이다.
	@Override
	public boolean preHandle(	HttpServletRequest request,
								HttpServletResponse response, 
								Object handler) throws Exception {
		
		log.debug("======================== START ========================");
		log.debug(" Request URL : " + request.getRequestURI());
		
//		return HandlerInterceptor.super.preHandle(request, response, handler);
		return true;
	}

	// 컨트롤러가 실행 후 수행할 것을 만들 것이다.
	@Override
	public void postHandle(	HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception {
		
		log.debug("======================== END ========================");
		
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
}
