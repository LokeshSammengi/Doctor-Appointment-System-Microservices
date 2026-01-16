package com.hospital.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.patient.exception.ApiResponse;
import com.hospital.patient.service.IPatientMgmtService;
import com.hospital.patient.vo.PatientVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientRestController {

	@Autowired
	private IPatientMgmtService patientService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> registerPatient(@Valid @RequestBody PatientVO vo){
		String resultMsg = patientService.createPatient(vo);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), resultMsg);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientVO> fetchPatientById(@PathVariable Long id){
		PatientVO vo = patientService.getPatientById(id);
		return new ResponseEntity<PatientVO>(vo,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PatientVO>> fetchAllPatients(){
		List<PatientVO> listVO = patientService.getAllPatients();
		return new ResponseEntity<List<PatientVO>>(listVO,HttpStatus.OK);
	}
	
	@PutMapping("/modify/{id}")
	public ResponseEntity<ApiResponse> modifyPatientById(@Valid @RequestBody PatientVO vo,
													@PathVariable Long id){
		String resultMsg = patientService.updatePatient(id, vo);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), resultMsg);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removePatientById(@PathVariable Long id){
		String resultMsg = patientService.deletePatientById(id);
		return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
	}
	
}
