package com.hospital.appointment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.appointment.entity.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

}
