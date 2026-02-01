package com.hospital.appointment.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.appointment.entity.Appointment;
import com.hospital.appointment.entity.Doctor;
import com.hospital.appointment.entity.Patient;
import com.hospital.appointment.exception.AppointmentIdNotFound;
import com.hospital.appointment.exception.DataIntegrityViolationException;
import com.hospital.appointment.exception.DoctorUnavaliableException;
import com.hospital.appointment.exception.InvalidInputException;
import com.hospital.appointment.exception.ResourceNotFoundException;
import com.hospital.appointment.exception.SlotAlreadyBookedException;
import com.hospital.appointment.repo.IAppointmentRepo;
import com.hospital.appointment.repo.IDoctoRepository;
import com.hospital.appointment.repo.IPatientRepository;
import com.hospital.appointment.vo.AppointmentVORequest;
import com.hospital.appointment.vo.AppointmentVOResponse;
import com.hospital.appointment.vo.DoctorVO;
import com.hospital.appointment.vo.PatientVO;

import jakarta.transaction.Transactional;

@Service
//@Transactional
public class AppointmentMgmtService implements IAppointmentMgmtService {

	@Autowired
	private IAppointmentRepo appointmentRepo;
	@Autowired
	private IDoctoRepository doctorRepo;
	@Autowired
	private IPatientRepository patientRepo;
	
	

	// methods
	/*
	 * public Doctor validateDoctor(Long id) { Doctor doctorEntity =
	 * doctorRepo.findById(id) .orElseThrow(() -> new
	 * ResourceNotFoundException("Doctor", "ID", id)); return doctorEntity; }
	 */
	public Doctor validateDoctor(Long id) {
		Doctor doctor = doctorRepo.findDoctorForBooking(id)
				.orElseThrow(() -> new DataIntegrityViolationException("Doctor", "id", id));
		return doctor;
	}

	public Patient validatePatient(Long id) {
		Patient patientEntity = patientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "ID", id));
		return patientEntity;
	}

	public LocalDate checkDoctorAvaliability(Doctor doc, AppointmentVORequest vo) {
		Doctor doctor = validateDoctor(vo.getDoctor().getDoctorId());
		if (vo.getAppointmentTime().isAfter(doctor.getAvailableFrom())
				&& vo.getAppointmentTime().isBefore(doctor.getAvailableTo())) {
			return vo.getAppointmentDate();
		} else {
			throw new DoctorUnavaliableException("doctor unavaliable exception");
		}
	}

	private void validateAppointmentDate(LocalDate date) {
		if (date.isBefore(LocalDate.now())) {
			throw new InvalidInputException("Appointment date cannot be in the past");
		}
	}

	private void checkDoctorAvailability(Doctor doctor, LocalTime time) {
		if (time.isAfter(doctor.getAvailableTo()) || time.isBefore(doctor.getAvailableFrom())) {
			throw new DoctorUnavaliableException("Doctor is not avaliable at this time");

		}
	}

	
	private void checkSlotConflict(Long doctorId, LocalDate date, LocalTime time) {
		Doctor doc = validateDoctor(doctorId);
		boolean exists = appointmentRepo.existsByDoctorAndAppointmentDateAndAppointmentTime(doc, date, time);
		if (exists) {
			throw new SlotAlreadyBookedException("Appointment slot already booked for this doctor");
		}
	}

	private void saveAppointment(AppointmentVORequest vo, Doctor doctor, Patient patient) {

		Appointment appointment = new Appointment();
		BeanUtils.copyProperties(vo, appointment);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setStatus("Booked");
		appointment.setCreatedBy("Admin");
		appointmentRepo.save(appointment);
	}

	@Override
	@Transactional
	public String bookAppointment(AppointmentVORequest vo) {

		validateAppointmentDate(vo.getAppointmentDate());

		// doctor row locks her
		Doctor doctor = validateDoctor(vo.getDoctor().getDoctorId());

		checkDoctorAvailability(doctor, vo.getAppointmentTime());

		checkSlotConflict(vo.getDoctor().getDoctorId(), vo.getAppointmentDate(), vo.getAppointmentTime());

		Patient patient = validatePatient(vo.getPatient().getPatientID());

		saveAppointment(vo, doctor, patient);


		return "Appointment booked successfully";

	}

	@Override
	public AppointmentVOResponse getAppointmentByIdvalue(Long id) {
		
		Appointment entity	=appointmentRepo.findById(id)
				.orElseThrow(()->new IllegalArgumentException("invalid id number"));
		AppointmentVOResponse vo = new AppointmentVOResponse();
		PatientVO patvo = new PatientVO();
		DoctorVO docvo  = new DoctorVO();
		
		BeanUtils.copyProperties(entity,vo);
		
		Patient pat=entity.getPatient();
		BeanUtils.copyProperties(pat, patvo);
		
		Doctor doc = entity.getDoctor();
		BeanUtils.copyProperties(doc, docvo);
						
		vo.setDoctor(docvo);
		vo.setPatient(patvo);
		
		return vo;
	}

	@Override
	public List<AppointmentVOResponse> getAllAppointments() {
		List<Appointment> listEntity =appointmentRepo.findAll();
		List<AppointmentVOResponse> listVO = new ArrayList<AppointmentVOResponse>();
		listEntity.forEach(entity->{
			AppointmentVOResponse vo = new AppointmentVOResponse();
			BeanUtils.copyProperties(entity, vo);
			
			DoctorVO docvo = new DoctorVO();
			Doctor doc=entity.getDoctor();
			BeanUtils.copyProperties(doc, docvo);
			
			PatientVO patvo = new PatientVO();
			Patient pat=entity.getPatient();
			BeanUtils.copyProperties(pat, patvo);
			
			vo.setDoctor(docvo);
			vo.setPatient(patvo);
			
			listVO.add(vo);			
		});
		return listVO;
	}

	@Override
	@Transactional
	public String modifyAppointmentById(Long id, AppointmentVORequest new_vo) {
		//check first id is visible or not
		Appointment entity = appointmentRepo.findById(id).orElseThrow(()->new AppointmentIdNotFound("Appointment", "id", id));
		//validate appointment date 
		validateAppointmentDate(new_vo.getAppointmentDate());
		//check whether doc is available or not 
		Doctor doc =validateDoctor(new_vo.getDoctor().getDoctorId());
		//check whether pat is available or not
		Patient pat =validatePatient(new_vo.getPatient().getPatientID());
		//check timings are perfect or not
		checkDoctorAvailability(doc, new_vo.getAppointmentTime());
		//check slot conflict 
		checkSlotConflict(new_vo.getDoctor().getDoctorId(), new_vo.getAppointmentDate(), new_vo.getAppointmentTime());
		
		//update the entity 
		entity.setAppointmentDate(new_vo.getAppointmentDate());
		entity.setAppointmentTime(new_vo.getAppointmentTime());
		//set relationship entity
		entity.setDoctor(doc);
		entity.setPatient(pat);
		//save the entity
		appointmentRepo.save(entity);
		
		return "Appointment updated id : "+id;
	}

	@Override
	public String removeAppointmentById(Long id) {
		//hard deletion
		Appointment entity = appointmentRepo.findById(id)
				.orElseThrow(()->new AppointmentIdNotFound("Appointment", "id", id));
		appointmentRepo.deleteById(id);
		return "Appointment deleted id : "+id;
	}

	
	
}
