package com.hospital.appointment.exception;

public class DataIntegrityViolationException extends RuntimeException{
	
	public DataIntegrityViolationException(String resource,String field,Object value) {
		super(resource+" is not found with field "+ field + " : "+value);
	}

}
