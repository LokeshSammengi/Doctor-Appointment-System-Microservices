package com.hospital.service;

import com.hospital.entity.Billing;
import com.hospital.vo.BillingRequestVO;
import com.hospital.vo.BillingResponseVO;

public interface IBillingMgmtService {

	
	public BillingResponseVO generateBill(BillingRequestVO request);
	public Billing getBillByAppointmentId(Long id);
}
