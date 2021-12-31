package com.example.customers.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseDto<T> {
	
	private Boolean status;
	private Integer code;
	private String message;
	private T body;
//	List<T> listBody;
	
	public ResponseDto(Boolean status, Integer code, String message, T body) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.body = body;
	}
	
//	public ResponseDto(Boolean status, Integer code, String message, List<T> listBody) {
//		this.status = status;
//		this.code = code;
//		this.message = message;
//		this.listBody = listBody;
//	}

	public ResponseDto(Boolean status, Integer code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
