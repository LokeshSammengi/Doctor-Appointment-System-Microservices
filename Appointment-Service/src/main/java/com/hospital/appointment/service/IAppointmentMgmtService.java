package com.hospital.appointment.service;

import java.util.List;

import com.hospital.appointment.vo.AppointmentVORequest;


public interface IAppointmentMgmtService {

	//response
	public  String bookAppointment(AppointmentVORequest vo);
	public AppointmentVORequest getAppointmentByIdvalue(Long id); //used for intra communication in billing service
	public List<AppointmentVORequest> getAllAppointments();
	public String modifyAppointmentById(Long id,AppointmentVORequest vo);
	public String removeAppointmentById(Long id);
	

}
