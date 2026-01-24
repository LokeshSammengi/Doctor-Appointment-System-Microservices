package com.hospital.doctor.exception;

public class SameDoctorAndSpecExists extends RuntimeException{

	public SameDoctorAndSpecExists(String resource,String field,Object value){
		super(resource+" is exists in database conflict issues with "+field+" : "+value);
	}
}
