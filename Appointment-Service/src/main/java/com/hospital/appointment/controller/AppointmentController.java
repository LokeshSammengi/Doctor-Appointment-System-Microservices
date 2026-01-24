package com.hospital.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.appointment.AppointmentServiceApplication;
import com.hospital.appointment.exception.ApiResponse;
import com.hospital.appointment.service.IAppointmentMgmtService;
import com.hospital.appointment.vo.AppointmentVO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceApplication appointmentServiceApplication;

	@Autowired
	private IAppointmentMgmtService appointmentService;


    AppointmentController(AppointmentServiceApplication appointmentServiceApplication) {
        this.appointmentServiceApplication = appointmentServiceApplication;
    }
	
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> saveAppointment(@Valid @RequestBody AppointmentVO vo){
		String resultMsg = appointmentService.bookAppointment(vo);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(),resultMsg);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/fetchById/{id}")
	public ResponseEntity<AppointmentVO> fetchAppointmentById(@PathVariable long id){
		AppointmentVO vo=appointmentService.getAppointmentByIdvalue(id);
		return ResponseEntity.ok(vo);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<ApiResponse> fetchAllAppointments(){
		List<AppointmentVO>listVO=appointmentService.getAllAppointments();
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(),listVO);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateAppointmentById(@PathVariable Long id ,@RequestBody AppointmentVO vo){
		String resultMsg = appointmentService.modifyAppointmentById(id, vo);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), resultMsg);
		return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<ApiResponse> deleteAppointmentById(@PathVariable Long id){
		String resultMsg=appointmentService.removeAppointmentById(id);
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), resultMsg);
		return ResponseEntity.ok(response);
	}
	
}
