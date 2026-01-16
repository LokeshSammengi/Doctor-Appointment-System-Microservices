package com.hospital.doctor.controller;

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

import com.hospital.doctor.entity.Doctor;
import com.hospital.doctor.exception.ApiResponse;
import com.hospital.doctor.service.IDoctorMgmtService;
import com.hospital.doctor.vo.DoctorVO;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

	@Autowired
	private IDoctorMgmtService doctorService;
	
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> registerDoctor(@Valid @RequestBody DoctorVO vo){
		String resultMsg = doctorService.createDoctor(vo);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), resultMsg);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DoctorVO> fetchDoctorById(@PathVariable Long id){
		DoctorVO vo = doctorService.getDoctorById(id);
		return new ResponseEntity<DoctorVO>(vo,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DoctorVO>> fetchAllDoctors(){
		List<DoctorVO> listVO = doctorService.getAllDoctors();
		return new ResponseEntity<List<DoctorVO>>(listVO,HttpStatus.OK);
	}
	
	@PutMapping("/modify/{id}")
	public ResponseEntity<ApiResponse> modifyDoctorById(@Valid @RequestBody DoctorVO vo,
													@PathVariable Long id){
		String resultMsg = doctorService.updateDoctorById(id, vo);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), resultMsg);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeDoctorById(@PathVariable Long id){
		String resultMsg = doctorService.deleteDoctor(id);
		return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
	}
}
