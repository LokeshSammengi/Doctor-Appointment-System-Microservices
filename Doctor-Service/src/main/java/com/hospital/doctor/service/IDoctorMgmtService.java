package com.hospital.doctor.service;

import java.util.List;

import com.hospital.doctor.vo.DoctorVO;

public interface IDoctorMgmtService {
	
	public String createDoctor(DoctorVO vo);
	public DoctorVO getDoctorById(Long id);
	public List<DoctorVO> getAllDoctors();
	public String updateDoctorById(Long id,DoctorVO vo);
	public String deleteDoctor(Long id);
}
