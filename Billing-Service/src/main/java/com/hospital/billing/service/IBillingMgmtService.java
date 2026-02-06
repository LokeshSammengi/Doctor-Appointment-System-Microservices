package com.hospital.billing.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hospital.billing.entity.Billing;
import com.hospital.billing.vo.BillingCompleteDetails;
import com.hospital.billing.vo.BillingRequestVO;
import com.hospital.billing.vo.BillingResponseVO;

public interface IBillingMgmtService {

	
	public BillingResponseVO generateBill(BillingRequestVO request);
	public BillingCompleteDetails getBillByAppointmentId(Long id);
	
	public List<BillingResponseVO>  getAllBills();
	public List<BillingCompleteDetails> getAllBillsByDate(LocalDateTime start,LocalDateTime end);
	
	public String deleteBillById(Long id);
	//customize with date and getall payments and delete the payment billing
}
