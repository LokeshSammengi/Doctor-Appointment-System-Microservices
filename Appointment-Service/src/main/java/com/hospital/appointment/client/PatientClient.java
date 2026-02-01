package com.hospital.appointment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospital.appointment.vo.PatientVO;

@FeignClient(name ="Patient-Service")
public interface PatientClient {

	@GetMapping("/patient/{id}")
	public PatientVO fetchPatientById(@PathVariable Long id);
}
