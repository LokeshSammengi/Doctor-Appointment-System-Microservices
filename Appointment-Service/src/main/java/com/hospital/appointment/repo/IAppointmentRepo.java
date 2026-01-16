package com.hospital.appointment.repo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.appointment.entity.Appointment;
import com.hospital.appointment.entity.Doctor;

public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {

	 boolean existsByDoctorAndAppointmentDateAndAppointmentTime(
	            Doctor doctor,
	            LocalDate appointmentDate,
	            LocalTime appointmentTime
	    );
}
