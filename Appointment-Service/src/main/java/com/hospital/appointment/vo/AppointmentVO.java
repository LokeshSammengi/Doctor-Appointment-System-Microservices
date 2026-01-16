package com.hospital.appointment.vo;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentVO {
	
	private Long appointmentId;

	@NotNull(message = "Appointment date is required")
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate appointmentDate;
	
	@NotNull(message = "Appointement Time is required")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime appointmentTime;
	
	@NotNull(message = "Doctor id is required")
	private Long doctorID;
	
	@NotNull(message = "Patient id is required")
	private Long patientID;
}
