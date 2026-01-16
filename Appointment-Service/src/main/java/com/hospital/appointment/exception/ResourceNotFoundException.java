package com.hospital.appointment.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String resource,String field,Object value) {
		super(resource+" is not found with field "+ field + " : "+value);
	}

}
