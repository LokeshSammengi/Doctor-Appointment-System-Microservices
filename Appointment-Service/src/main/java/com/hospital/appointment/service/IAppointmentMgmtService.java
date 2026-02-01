package com.hospital.appointment.service;

import java.util.List;

import com.hospital.appointment.vo.AppointmentVORequest;
import com.hospital.appointment.vo.AppointmentVOResponse;

public interface IAppointmentMgmtService {

	//response
	public  String bookAppointment(AppointmentVORequest vo);
	public AppointmentVOResponse getAppointmentByIdvalue(Long id); //used for intra communication in billing service
	public List<AppointmentVOResponse> getAllAppointments();
	public String modifyAppointmentById(Long id,AppointmentVORequest vo);
	public String removeAppointmentById(Long id);
	

}
