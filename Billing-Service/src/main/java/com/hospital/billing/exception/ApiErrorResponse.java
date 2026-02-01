package com.hospital.billing.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.hospital.appointment.vo.AppointmentVORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

	private LocalDateTime timestamp;
	

	private String message;
	

    public ApiErrorResponse(int status, String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;       
    }
	


	
	
    
}
