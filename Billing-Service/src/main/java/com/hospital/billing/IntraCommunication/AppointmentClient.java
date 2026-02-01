package com.hospital.billing.IntraCommunication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospital.appointment.vo.AppointmentVOResponse;



@FeignClient(name = "Appointment-Service")
public interface AppointmentClient {

	@GetMapping("/appointment/fetchById/{id}")
	public AppointmentVOResponse fetchAppointmentById(@PathVariable Long id);
		
}
