package com.hospital.appointment.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> errorHandle(ResourceNotFoundException error){
ApiResponse er = new ApiResponse(HttpStatus.NOT_FOUND.value(),error.getMessage());
		return new ResponseEntity<ApiResponse>(er,HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler(value = InvalidInputException.class)
	public ResponseEntity<ApiResponse> handleInvalidInputException(InvalidInputException error){
		ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(),error.getMessage());		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        String errorMsg = ex.getBindingResult()
                           .getFieldError()
                           .getDefaultMessage();

        ApiResponse response =
                new ApiResponse(HttpStatus.BAD_REQUEST.value(), errorMsg);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
	


}
