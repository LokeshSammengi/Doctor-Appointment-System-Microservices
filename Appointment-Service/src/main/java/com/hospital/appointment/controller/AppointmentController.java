package com.hospital.appointment.controller;

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

import com.hospital.appointment.client.DoctorClient;
import com.hospital.appointment.client.PatientClient;
import com.hospital.appointment.exception.ApiResponse;
import com.hospital.appointment.service.IAppointmentMgmtService;
import com.hospital.appointment.vo.AppointmentVORequest;
import com.hospital.appointment.vo.AppointmentVOResponse;
import com.hospital.appointment.vo.DoctorVO;
import com.hospital.appointment.vo.PatientVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentMgmtService appointmentService;
	
	//client communication
		@Autowired
		private PatientClient patClient;
		@Autowired
		private DoctorClient docClient;

	@PostMapping("/save")
	public ResponseEntity<ApiResponse> saveAppointment(@Valid @RequestBody AppointmentVORequest vo){
		
		Long docid = vo.getDoctor().getDoctorId();
		DoctorVO docvo=docClient.fetchDoctorById(docid);
		
		if(docvo == null) {
			throw new RuntimeException("NO doctor avaliable with this id "+docid);
		}
		
		Long patid = vo.getPatient().getPatientID();
		PatientVO patvo=patClient.fetchPatientById(patid);
		if(patvo == null) {
			throw new RuntimeException("No patient avaliable with this id "+patid);
		}
		
		vo.setDoctor(docvo);
		vo.setPatient(patvo);		
		
		String resultMsg = appointmentService.bookAppointment(vo);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(),resultMsg);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/fetchById/{id}")
	public ResponseEntity<AppointmentVOResponse> fetchAppointmentById(@PathVariable Long id){
		
		AppointmentVOResponse vo=appointmentService.getAppointmentByIdvalue(id);
		
		return new ResponseEntity<AppointmentVOResponse>(vo,HttpStatus.OK);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<ApiResponse> fetchAllAppointments(){
		List<AppointmentVOResponse>listVO=appointmentService.getAllAppointments();
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(),listVO);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateAppointmentById(@PathVariable Long id ,@RequestBody AppointmentVORequest req){
		String resultMsg = appointmentService.modifyAppointmentById(id, req);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), resultMsg);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteAppointmentById(@PathVariable Long id){
		String resultMsg=appointmentService.removeAppointmentById(id);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), resultMsg);
		return ResponseEntity.ok(response);
	}
	
}
