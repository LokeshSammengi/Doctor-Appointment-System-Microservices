package com.hospital.billing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = AppointmentNotCompleted.class)
	public ResponseEntity<ApiErrorResponse> appointmentStatusError(AppointmentNotCompleted errorResp){
		ApiErrorResponse response = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),errorResp.getMessage());
		return new ResponseEntity<ApiErrorResponse>(response,HttpStatus.BAD_REQUEST);
		
	}

}
