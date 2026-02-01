package com.hospital.appointment.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.hospital.appointment.vo.AppointmentVOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private LocalDateTime timestamp;
	

	private String message;
	
//	private List<AppointmentVORequest> listvo;
	
	private List<AppointmentVOResponse> listvoresponse;
	
	private Integer statusCode;
	
//	@NonNull
//	private T data; ->gives the data what you given in json format

    public ApiResponse(int status, String message) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = status;
        this.message = message;       
    }
	
    public ApiResponse(int status,List<AppointmentVOResponse> listvoresponse) {
    	this.timestamp = LocalDateTime.now();
    	this.statusCode = status;
    	this.listvoresponse = listvoresponse;
    }

	
	
    
}
