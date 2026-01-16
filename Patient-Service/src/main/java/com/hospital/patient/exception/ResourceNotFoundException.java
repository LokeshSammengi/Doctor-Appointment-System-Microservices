package com.hospital.patient.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String resource,String field,Object id) {
		super(resource+" not found with "+field+" : "+id);
	}

}
