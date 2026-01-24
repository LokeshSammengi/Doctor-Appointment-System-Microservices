package com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.entity.Billing;
import com.hospital.service.IBillingMgmtService;
import com.hospital.vo.BillingRequestVO;
import com.hospital.vo.BillingResponseVO;

@RestController
@RequestMapping("/billing")
public class BillingRestController {

	@Autowired
	private IBillingMgmtService service;
	
	@PostMapping("/payment")
	public ResponseEntity<BillingResponseVO> BillDetails(@RequestBody BillingRequestVO vo){
	BillingResponseVO response = service.generateBill(vo);
	return new ResponseEntity<BillingResponseVO>(response,HttpStatus.OK);
	}
	
	@GetMapping("/appointmentBill/{id}")
	public ResponseEntity<Billing> getBillDetails(@PathVariable Long id){
		Billing bill=service.getBillByAppointmentId(id);
//		return new ResponseEntity<Billing>(bill,HttpStatus.OK);
		return ResponseEntity.ok(bill);
	}
}
