package com.hospital.patient.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.patient.entity.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

}
