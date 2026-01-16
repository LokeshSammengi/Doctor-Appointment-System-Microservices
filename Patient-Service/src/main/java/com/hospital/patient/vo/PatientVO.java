package com.hospital.patient.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientVO {
	
	
	private Long patientID;
	
	@NotBlank(message = "Patient Name is required")
	private String name;
	
	@NotNull
	@Positive(message = "Must be greater than zero")
	private Integer age;
	
	 @NotBlank(message = "Gender is required")
	    @Pattern(
	        regexp = "Male|Female|Other|male|female|other",
	        message = "Gender must be Male, Female, or Other"
	    )
	private String gender;
	
	 @NotBlank(message = "Phone number is required")
	    @Pattern(
	        regexp = "^[6-9][0-9]{9}$",
	        message = "Phone number must be a valid 10-digit Indian mobile number"
	    )
	private String phoneNumber;
	
	 @NotBlank(message = "Email is required")
	 @Email(message = "Email format must be like xxxx@example.com")
	private String email;
	
	

}

