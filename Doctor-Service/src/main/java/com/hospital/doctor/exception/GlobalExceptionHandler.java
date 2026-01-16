package com.hospital.doctor.exception;

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

	@ExceptionHandler(value = ResourceNotFoundExcepiton.class)
	public ResponseEntity<ErrorResponse> errorHandle(ResourceNotFoundExcepiton error){
		ErrorResponse er = new ErrorResponse(HttpStatus.NOT_FOUND.value(),error.getMessage());
		return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);		
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
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors()
//                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(
//	        MethodArgumentNotValidException ex) {
//
//	    Map<String, String> errors = new HashMap<>();
//
//	    ex.getBindingResult().getFieldErrors().forEach(error ->
//	            errors.put(error.getField(), error.getDefaultMessage())
//	    );
//
//	    ApiResponse<Map<String, String>> response =
//	            new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
//	                    "Validation failed");
//
//	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}

}
