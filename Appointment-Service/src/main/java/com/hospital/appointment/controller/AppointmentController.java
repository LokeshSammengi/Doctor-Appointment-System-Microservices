package com.hospital.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.appointment.exception.ApiResponse;
import com.hospital.appointment.service.IAppointmentMgmtService;
import com.hospital.appointment.vo.AppointmentVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentMgmtService appointmentService;
	
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> saveAppointment(@Valid @RequestBody AppointmentVO vo){
		String resultMsg = appointmentService.bookAppointment(vo);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(),resultMsg);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
}
