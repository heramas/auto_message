package com.test.source;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CodeTest {

	@NotNull(message = "이름은 필수 입니다.")
	private String name;
	
	@NotNull(message = "비밀번호는 필수 입니다.")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "비밀 번호는 6~20자리로 숫자와 특수 문자가 포함된 영문 대소문자로 입력해 주세요")
    private String password;

	@Email(message = "이메일 형식으로 입력해 주세요")
    private String email;
    
	@Length(min = 1, max = 10, message = "전화 번호는 10자리 이하로 입력해 주세요")
    private String phoneNumber;
	
	 public static void main(String[] args) {
		 
	
	}

}

