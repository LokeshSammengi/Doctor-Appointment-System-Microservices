package com.hospital.billing.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.billing.entity.Billing;
import com.hospital.billing.service.IBillingMgmtService;
import com.hospital.billing.vo.BillingCompleteDetails;
import com.hospital.billing.vo.BillingRequestVO;
import com.hospital.billing.vo.BillingResponseVO;

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
	public ResponseEntity<BillingCompleteDetails> getBillDetails(@PathVariable Long id){
		BillingCompleteDetails bill=service.getBillByAppointmentId(id);
//		return new ResponseEntity<Billing>(bill,HttpStatus.OK);
		return new ResponseEntity<BillingCompleteDetails>(bill,HttpStatus.OK);
	}
	
	@GetMapping("/fetchingAllResponse")
	public ResponseEntity<List<BillingResponseVO>> fetchAllBills(){
		List<BillingResponseVO> response = service.getAllBills();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/fetchByDate/{start}/{end}")
	public ResponseEntity<List<BillingCompleteDetails>> fetchBillsByDateRange(@PathVariable LocalDateTime start,@PathVariable LocalDateTime end){
		List<BillingCompleteDetails> responsebill = service.getAllBillsByDate(start, end);	
		return ResponseEntity.ok(responsebill);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeBill(@PathVariable Long id){
		String msg = service.deleteBillById(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
}	
