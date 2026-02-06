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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class AppointmentVORequest {
	
	private Long appointmentId;

	@NonNull
	@NotNull(message = "Appointment date is required")
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate appointmentDate;
	
	@NonNull
	@NotNull(message = "Appointement Time is required")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime appointmentTime;
	
	@NonNull
	@NotNull(message = "Doctor id is required")
	private Long doctorID;
	
	@NonNull
	@NotNull(message = "Patient id is required")
	private Long patientID;
	
	private String status;
	
	
	
	
}
