package com.auto.message.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private long 	idx;
	private String 	email;
	private String 	name;
	private Date  	date;
	private String  calls;
	
	public Member() {
	}
	
	public Member(long idx, String email, String name) {
	    this.email = email;
	    this.name = name;
	}
	public Member(String name, Date date, String calls) {
		this.name = name;
		this.date = date;
		this.calls = calls;
	}
	
	public String toString() {
		
		return "\nO > -----------------------------------------------"+
				"\nO >  Member "+
				"\nO > -----------------------------------------------"+
				"\nO > idx      : "+ idx +
				"\nO > email    : "+ email +
				"\nO > name     : "+ name +
				"\nO > date     : "+ date +
				"\nO > calls    : "+ calls +
				"\nO > -----------------------------------------------"+
				"\n";
	}
}
