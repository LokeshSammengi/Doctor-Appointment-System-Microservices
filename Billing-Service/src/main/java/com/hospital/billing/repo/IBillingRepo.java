package com.hospital.billing.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hospital.billing.entity.Billing;

public interface IBillingRepo extends JpaRepository<Billing,Long> {

	Optional<Billing> findByAppointmentId(Long appointmentId);
//	@Query(//"select * from billing where createdOn >?1 && createdOn <?2 order by totalAmount")
//	Optional<List<Billing>> getBillsByDateRange(LocalDateTime start,LocalDateTime end);
	
	@Query("SELECT b FROM Billing b WHERE b.createdOn BETWEEN :start AND :end ORDER BY b.totalAmount")
		List<Billing> getBillsByDateRange(
		        @Param("start") LocalDateTime start,
		        @Param("end") LocalDateTime end
		);

}
