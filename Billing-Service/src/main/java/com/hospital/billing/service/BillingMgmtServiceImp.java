package com.hospital.billing.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.appointment.vo.AppointmentVORequest;
import com.hospital.billing.IntraCommunication.AppointmentClient;
import com.hospital.billing.entity.Billing;
import com.hospital.billing.entity.PaymentStatus;
import com.hospital.billing.repo.IBillingRepo;
import com.hospital.billing.vo.AppointmentVO;
import com.hospital.billing.vo.BillingCompleteDetails;
import com.hospital.billing.vo.BillingRequestVO;
import com.hospital.billing.vo.BillingResponseVO;

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
//		AppointmentVORequest vo = client.fetchAppointmentById(request.getAppointmentId());
		AppointmentVORequest vo=client.fetchAppointmentById(request.getAppointmentId());
		AppointmentVO Bill_Appoint_VO = new AppointmentVO();
		BeanUtils.copyProperties(vo, Bill_Appoint_VO);
		if(!"completed".equals(Bill_Appoint_VO.getStatus())) {
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
	
	
	public BillingCompleteDetails getBillByAppointmentId(Long id) {
		Billing billentity = billRepo.findByAppointmentId(id)
				     		.orElseThrow(()-> new RuntimeException("Bill Not Found By this Id :"+id));
		BillingCompleteDetails bill = new BillingCompleteDetails();
		BeanUtils.copyProperties(billentity, bill);
		return bill;
	}


	@Override
	public List<BillingResponseVO> getAllBills() {
		List<Billing> list_bill=billRepo.findAll();
		List<BillingResponseVO> list_billRespVo = new ArrayList<BillingResponseVO>();
		list_bill.forEach(bill->{
			BillingResponseVO billrespvo = new BillingResponseVO();
			BeanUtils.copyProperties(bill, billrespvo);
			list_billRespVo.add(billrespvo);
			
		});
		return list_billRespVo;
	}


	@Override
	public List<BillingCompleteDetails> getAllBillsByDate(LocalDateTime start, LocalDateTime end) {
		List<Billing> BillEntity=billRepo.getBillsByDateRange(start, end);
		
		List<BillingCompleteDetails> listbillcompleteDetails = new ArrayList<>();
		BillEntity.forEach(entity->{
			BillingCompleteDetails bcd = new BillingCompleteDetails();
			BeanUtils.copyProperties(entity, bcd);
			listbillcompleteDetails.add(bcd);			
		});
		return listbillcompleteDetails;		
	}


	@Override
	public String deleteBillById(Long id) {
		billRepo.deleteById(id);
		return "Billing deleted succuessfully from the database with id ::"+id;
	}

}
