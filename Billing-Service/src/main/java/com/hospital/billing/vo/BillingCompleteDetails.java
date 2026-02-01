package com.hospital.billing.vo;

import com.hospital.billing.entity.PaymentStatus;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BillingCompleteDetails {

	private Long billId;
	
	private Double consulationFee;
	private Double serviceCharges;
	private Double discount;
	private Double totalAmount;
	private PaymentStatus paymentStatus;
	
	private Long appointmentId;
	private Long patientId;
	private Long doctorId;
	
	
	
	
}
