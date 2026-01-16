package com.hospital.doctor.exception;

public class ResourceNotFoundExcepiton extends RuntimeException{

	public ResourceNotFoundExcepiton(String resource,String field,Object value){
		super(resource+" is not found with field "+field+" : "+value);
	}
}
