package com.hospital.doctor.vo;


import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DoctorVO {

	private Long doctorId;	
	
	@NotBlank(message = "Doctor name is required")
	private String name;
	
	@NotBlank(message = "Specialization is required")
	private String specialization;
	
	@NotNull(message = "Consultation fee is required")
	@Positive(message = "Consultation fee must be greater than 0")
	private Double consulationFee;
	
	@NotNull(message = "Available From time is required")
	private LocalTime availableFrom;
	
	@NotNull(message = "Available To time is required")
	private LocalTime availableTo;
	
	
	
	
}
