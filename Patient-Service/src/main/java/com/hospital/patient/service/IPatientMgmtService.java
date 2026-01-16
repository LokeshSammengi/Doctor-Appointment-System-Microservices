package com.hospital.patient.service;

import java.util.List;

import com.hospital.patient.vo.PatientVO;

public interface IPatientMgmtService {

	public String createPatient(PatientVO vo);
	public PatientVO getPatientById(Long id);
	public List<PatientVO> getAllPatients();
	public String updatePatient(Long id,PatientVO vo);
	public String deletePatientById(Long id);
}
