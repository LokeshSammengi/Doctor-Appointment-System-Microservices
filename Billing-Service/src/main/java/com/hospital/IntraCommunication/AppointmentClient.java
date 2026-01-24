package com.hospital.IntraCommunication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospital.vo.AppointmentVO;



@FeignClient(name = "Appointment-Service")
public interface AppointmentClient {

	@GetMapping("/getapp/{id}")
	public AppointmentVO  fetchAppointmentById(@PathVariable Long id);
}
