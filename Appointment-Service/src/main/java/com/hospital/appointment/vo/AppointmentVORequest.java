package com.hospital.appointment.vo;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hospital.appointment.entity.Doctor;
import com.hospital.appointment.entity.Patient;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentVORequest {
	
	private Long appointmentId;

	@NotNull(message = "Appointment date is required")
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate appointmentDate;
	
	@NotNull(message = "Appointement Time is required")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime appointmentTime;
	
	@NotNull(message = "Doctor id is required")
	private DoctorVO doctor;
	
	@NotNull(message = "Patient id is required")
	private PatientVO patient;
	
	
}
