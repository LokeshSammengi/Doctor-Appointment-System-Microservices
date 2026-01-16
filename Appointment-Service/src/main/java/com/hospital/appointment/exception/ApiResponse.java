package com.hospital.appointment.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private LocalDateTime timestamp;
	

	private String message;
	

	private Integer statusCode;
	
//	@NonNull
//	private T data; ->gives the data what you given in json format

    public ApiResponse(int status, String message) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = status;
        this.message = message;       
    }
	
}
