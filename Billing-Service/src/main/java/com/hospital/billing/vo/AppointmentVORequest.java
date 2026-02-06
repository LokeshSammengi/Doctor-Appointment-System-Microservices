package com.hospital.billing.vo;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentVORequest {
	
	private Long appointmentId;

	private LocalDate appointmentDate;
	
	private LocalTime appointmentTime;
	
//	private DoctorVO doctor;
//	
//	private PatientVO patient;
	
	private String status;
	
	
}
