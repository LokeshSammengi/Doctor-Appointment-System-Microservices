package com.hospital.patient.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ApiResponse {

	private LocalDateTime timeStamp;
	private Integer statusCode;
	private String message;
	
	public ApiResponse(Integer statuscode,String message) {
		this.timeStamp = LocalDateTime.now();
		this.statusCode = statuscode;
		this.message = message;
	}
}
