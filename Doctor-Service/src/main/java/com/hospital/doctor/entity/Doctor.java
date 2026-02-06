package com.hospital.doctor.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Hospital_Doctor_DB")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE Hospital_Doctor_DB SET active_switch='inactive' WHERE doctor_id=? AND update_count=?")
@SQLRestriction("active_switch<>'inactive'")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	
	@Column(length=20)
	@NonNull
	private String name;

	@Column(length=20)
	@NonNull
	private String specialization;

	@NonNull
	private Double consulationFee;
	
	@NonNull
	private LocalTime availableFrom;
	
	@NonNull
	private LocalTime availableTo;
	
	
	//Meta data properties
	
	@Version
	private Integer updateCount;
	
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	
	@Column(length = 20)
	private String createdBy;

	@Column(length = 20)
	private String updatedBy;

	@Column(length = 20)
	private String activeSwitch="ACTIVE";
	
}
