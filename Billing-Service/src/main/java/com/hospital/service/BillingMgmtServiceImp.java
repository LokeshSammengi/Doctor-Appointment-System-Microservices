package com.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.IntraCommunication.AppointmentClient;
import com.hospital.entity.Billing;
import com.hospital.entity.PaymentStatus;
import com.hospital.repo.IBillingRepo;
import com.hospital.vo.AppointmentVO;
import com.hospital.vo.BillingRequestVO;
import com.hospital.vo.BillingResponseVO;

import jakarta.transaction.Transactional;

@Service
public class BillingMgmtServiceImp implements IBillingMgmtService {

	@Autowired
	private IBillingRepo billRepo;
	
	@Autowired
	private AppointmentClient client;
	
	@Override
	@Transactional
	public BillingResponseVO generateBill(BillingRequestVO request) {
		
		//intra communication using 
		AppointmentVO vo = client.fetchAppointmentById(request.getAppointmentId());
		
		if(!vo.getStatus().equalsIgnoreCase("completed")) {
			throw new RuntimeException("Bill can be generated only for completed appointments");
		}
		
		double tax = (request.getConsulationFee()+request.getServiceCharges())*0.05;
		double totalAmount = ((request.getConsulationFee()+request.getServiceCharges())
							+tax )-request.getDiscount();
		
		Billing billing = new Billing();
		billing.setAppointmentId(request.getAppointmentId());
		billing.setDoctorId(request.getDoctorId());
		billing.setPatientId(request.getPatientId());
		billing.setConsulationFee(request.getConsulationFee());
		billing.setServiceCharges(request.getServiceCharges());
		billing.setTax(tax);
		billing.setDiscount(request.getDiscount());
		billing.setTotalAmount(totalAmount);
		billing.setPaymentStatus(PaymentStatus.PAID);
		
		Billing savedBill=billRepo.save(billing);
		
		return new BillingResponseVO(savedBill.getBillId(),savedBill.getTotalAmount(),savedBill.getPaymentStatus());
	}
	
	
	public Billing getBillByAppointmentId(Long id) {
		return billRepo.findByAppointmentId(id)
				     		.orElseThrow(()-> new RuntimeException("Bill Not Found By this Id :"+id));
	}

}
