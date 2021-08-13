package com.auto.message.configuration;

import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * 1. Client 에서 GET 방식으로 home url 자원접근 요청
	 * 2. Server 에서는 인증된 사용자만 접근가능하다고 판단해 인증이 안되면 로그인 페이지로 리다이렉트
	 * 3. Client 는 로그인페이지의 username / passwrod 입력하여 POST 방식으로 인증 시도
	 * 4. Server 에서는 Session ID 생성 후 인증결과를 담은 인증 토큰( Authentication ) 생성/저장
	 * 5. Client 에서 '/home' 접근요청 시 세션에 저장된 인증 토큰으로 접근/인증 유지
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()							// form 로그인 인증 기능이 작동
			.loginPage("/login.html")				// 사용자 정의 로그인 페이지
			.defaultSuccessUrl("/home")				// 로그인 성공 후 이동 페이지
			.failureUrl("/login.html?error=true")	// 로그인 실패 후 이동 페이지
			.usernameParameter("username")			// 아이디 파라미터명 설정
			.passwordParameter("password")			// 패스워드 파라미터명 설정
			.loginProcessingUrl("/login")			// 로그인 form action url
//			.successHandler(loginSuccessHandler())	// 로그인 성공 후 핸들러 ( 해당 핸들러를 생성하여 핸들링 해준다. )
			.successHandler(new AuthenticationSuccessHandler() { // 핸들러를 따로 안만들고 아래와 처럼 만들 수 있다.
				
				@Override
				public void onAuthenticationSuccess(	HttpServletRequest 	request, 
														HttpServletResponse response,
														Authentication 		authentication ) throws IOException, ServletException {
					System.out.println("Authentication :: " + authentication.getName());
					response.sendRedirect("/");
				}
			})
//			.failureHandler(loginFailureHandler());	// 로그인 실패 후 핸들러 ( 해당 핸들러를 생성하여 핸들링 해준다. )
			.failureHandler(new AuthenticationFailureHandler() { // 핸들러를 따로 안만들고 아래와 처럼 만들 수 있다.
				
				@Override
				public void onAuthenticationFailure(	HttpServletRequest 		request,
														HttpServletResponse 	response,
														AuthenticationException e ) throws IOException, ServletException {
					System.out.println("exception :: " + e.getMessage());
					response.sendRedirect("/login");
				}
			})	
			.permitAll();							// 사용자 정의 로그인 페이지 접근 권한 승인
			
			
		super.configure(http);
	}

}
