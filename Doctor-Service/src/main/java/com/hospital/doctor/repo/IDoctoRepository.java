package com.hospital.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.doctor.entity.Doctor;

public interface IDoctoRepository extends JpaRepository<Doctor, Long> {

}
