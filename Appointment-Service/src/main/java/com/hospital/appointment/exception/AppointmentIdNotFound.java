package com.hospital.appointment.exception;

public class AppointmentIdNotFound extends RuntimeException{
	
	public AppointmentIdNotFound(String resource,String field,Object value) {
		super(resource+" is not found with field "+ field + " : "+value);
	}

}
