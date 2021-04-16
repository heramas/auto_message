package com.auto.message.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class CORSFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("[FILTER] init CROSFilter");
//		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(	ServletRequest request, 
							ServletResponse response, 
							FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest	req = (HttpServletRequest)  request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		log.info("#### filter - before ####");
		chain.doFilter(req, res);
		log.info("#### filter - after ####");
		
	}

	@Override
	public void destroy() {
		log.info("destory CORSFilter");
//		Filter.super.destroy();
	}

	
}
