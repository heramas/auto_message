package com.auto.message.configuration;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auto.message.interceptor.LoggerInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	private static final String ENCRYPT_KEY = System.getProperty("encrypt_key");
	
	/**
	 * 인터셉터 config 설정
	 * excludePathPatterns 는 정적 리소스 파일 제외하기 위해 선언한다.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor())
		.excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
	}
	
	/**
	 * properties 에 있는 중요 정보를 암호/복호화 하기 위한 설정
	 * encrypt_key 는 vm option 설정된 값을 가져온다.
	 * @return
	 */
	@Bean("jasyptStringEncrptor")
	public StringEncryptor stringEncryptor() {
	
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor(); 
		SimpleStringPBEConfig config = new SimpleStringPBEConfig(); 
		config.setPassword(ENCRYPT_KEY); 
		config.setPoolSize("1"); 
		config.setStringOutputType("base64"); 
//		config.setKeyObtentionIterations("1000"); 
//		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); 
		encryptor.setConfig(config); 
		return encryptor; 
		
	}

	public static void main(String[] args) {
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm("PBEWithMD5AndDES");
		pbeEnc.setPassword("auto_message");
		
		String host		="mongodb://localhost:27017/admin";
		String port		="587";
		String username	="root";
		String password	="1q2w3e!@#";
		

		
		System.out.println("host:"+pbeEnc.encrypt(host));
		System.out.println("port:"+pbeEnc.encrypt(port));
		System.out.println("name:"+pbeEnc.encrypt(username));
		System.out.println("pass:"+pbeEnc.encrypt(password));
	}
	
}
