package com.auto.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor // 자동으로 모든 매개변수를 받는 생성자를 생성
@Data
public class UserDTO {
    private int seq;
    private String name;
    private String country;

}
