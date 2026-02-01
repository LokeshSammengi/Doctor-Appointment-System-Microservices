package com.hospital.appointment.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "hospital_appointment_data",uniqueConstraints = @UniqueConstraint(columnNames = {"doctorID","appointmentDate","appointmentTime"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Appointment {

	@Id
	@SequenceGenerator(name = "gen1",sequenceName = "app_seq",allocationSize = 1,initialValue = 100)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Long appointmentId;
	
	@NonNull
	@Column(nullable = false)
	private LocalDate appointmentDate;
	
	@NonNull
	@Column(nullable = false)
	private LocalTime appointmentTime;
	
	@Column(length = 20,nullable = false)
	@NonNull
	private String status;
	
	@NonNull
	@ManyToOne(targetEntity = Doctor.class,cascade = CascadeType.MERGE)
	@JoinColumn(name="doctor_id",referencedColumnName = "doctorId",nullable = false)
	private Doctor doctor;
	
	@NonNull
	@ManyToOne(targetEntity = Patient.class,cascade = CascadeType.MERGE)
	@JoinColumn(name = "patient_id",referencedColumnName = "patientID",nullable = false)
	private Patient patient;
	
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
	private String active_sw = "ACTIVE";
	
	
	
	
}
