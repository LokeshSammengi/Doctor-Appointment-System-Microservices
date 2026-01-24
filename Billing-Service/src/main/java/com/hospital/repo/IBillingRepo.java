package com.hospital.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.Billing;

public interface IBillingRepo extends JpaRepository<Billing,Long> {

	Optional<Billing> findByAppointmentId(Long appointmentId);
}
