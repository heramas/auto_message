package com.auto.message.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExceptionResult extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2449073088488158423L;
	
	private String resCode;
	private String resMsg;
	
	public ExceptionResult(String code) {
		this.resCode = code;
	}
	
	public ExceptionResult(String code, String msg) {
		this.resCode = code;
		this.resMsg  = msg;
	}
	
	public String toString() {
		
		return "\nO > -----------------------------------------------"+
				"\nO >  ExceptionResult "+
				"\nO > -----------------------------------------------"+
				"\nO > resCode   : "+ resCode +
				"\nO > resMsg    : "+ resMsg +
				"\nO > -----------------------------------------------"+
				"\n";
	}
}
