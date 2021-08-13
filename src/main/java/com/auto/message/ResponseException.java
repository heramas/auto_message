package com.auto.message;

import lombok.Data;

@Data
public class ResponseException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String errCode = "";
	private String errMsg  = "";
	
	public ResponseException (String errCode) {
		this.errCode = errCode;
	}
	
	public ResponseException (String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg  = errMsg;
	}
	
}
