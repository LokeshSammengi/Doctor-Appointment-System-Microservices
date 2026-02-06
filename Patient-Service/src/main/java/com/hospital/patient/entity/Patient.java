package com.hospital.patient.entity;

import java.time.LocalDateTime;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Hospital_patient_data")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Hospital_patient_data SET active_sw='inactive' WHERE patientid=? AND update_count=?")
@SQLRestriction("active_sw<>'inactive'")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientID;
	
	@Column(length = 20)
	@NonNull
	private String name;

	@Column(length = 20)
	@NonNull
	private Integer age;
	
	@Column(length = 20)
	@NonNull
	private String gender;
	
	
	@Column(length = 20)
	@NonNull
	private String phoneNumber;
	
	@Column(length = 40)
	@NonNull
	private String email;
	
	//meta data properties
	
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
	private String active_SW = "Active";

}
