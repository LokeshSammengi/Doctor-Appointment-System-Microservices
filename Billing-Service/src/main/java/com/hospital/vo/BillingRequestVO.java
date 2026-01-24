package com.hospital.vo;



import com.hospital.entity.PaymentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillingRequestVO {

	@NotNull
	private Long appointmentId;
	@NotNull
	private Long patientId;
	@NotNull
	private Long doctorId;
	
	
	private Double consulationFee;
	private Double serviceCharges;
	private Double discount;
	
	
}
