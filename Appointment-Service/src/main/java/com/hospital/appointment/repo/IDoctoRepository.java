package com.hospital.appointment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.appointment.entity.Doctor;

public interface IDoctoRepository extends JpaRepository<Doctor, Long> {

}
