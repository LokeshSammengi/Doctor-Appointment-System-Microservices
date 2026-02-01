package com.hospital.appointment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospital.appointment.vo.DoctorVO;

@FeignClient(name = "Doctor-Service")
public interface DoctorClient {
	
	@GetMapping("/doctor/{id}")
	public DoctorVO fetchDoctorById(@PathVariable Long id);
	
}
