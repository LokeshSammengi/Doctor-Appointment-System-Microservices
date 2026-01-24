package com.hospital.appointment.service;

import java.util.List;

import com.hospital.appointment.vo.AppointmentVO;

public interface IAppointmentMgmtService {

	//response
	public  String bookAppointment(AppointmentVO vo);
	public AppointmentVO getAppointmentByIdvalue(Long id); //used for intra communication in billing service
	public List<AppointmentVO> getAllAppointments();
	public String modifyAppointmentById(Long id,AppointmentVO vo);
	public String removeAppointmentById(Long id);

}
