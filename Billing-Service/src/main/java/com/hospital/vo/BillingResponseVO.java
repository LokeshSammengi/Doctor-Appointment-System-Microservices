package com.hospital.vo;

import com.hospital.entity.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingResponseVO {

	private Long billId;
	private Double totalAmount;
	private PaymentStatus paymentStatus;
}
