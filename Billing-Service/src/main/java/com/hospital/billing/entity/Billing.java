package com.hospital.billing.entity;

import java.time.LocalDateTime;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "Billing_Table")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Billing {

	@Id
	@SequenceGenerator(name = "seq_gen",sequenceName = "billing_seq",allocationSize = 1,initialValue = 1000)
	@GeneratedValue(generator = "seq_gen",strategy = GenerationType.SEQUENCE)
	private Long billId;
	
	private Long appointmentId;
	private Long patientId;
	private Long doctorId;
	
	
	private Double consulationFee;
	private Double serviceCharges;
	private Double tax;
	private Double discount;
	private Double totalAmount;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	//Meta data properties
	
	@CreationTimestamp
	private LocalDateTime createdOn;
}
