package com.hospital.appointment.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.hospital.appointment.entity.Doctor;

import jakarta.persistence.LockModeType;

public interface IDoctoRepository extends JpaRepository<Doctor, Long> {
	
	@Lock(LockModeType.OPTIMISTIC)
	@Query("SELECT d FROM Doctor d where d.doctorId = :id")
	Optional<Doctor> findDoctorForBooking(Long id);

}
