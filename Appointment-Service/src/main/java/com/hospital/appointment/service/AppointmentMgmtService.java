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
import com.hospital.appointment.vo.AppointmentVO;

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

	public LocalDate checkDoctorAvaliability(Doctor doc, AppointmentVO vo) {
		Doctor doctor = validateDoctor(vo.getDoctorID());
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
		boolean exists = appointmentRepo.existsByDoctorIDAndAppointmentDateAndAppointmentTime(doc, date, time);
		if (exists) {
			throw new SlotAlreadyBookedException("Appointment slot already booked for this doctor");
		}
	}

	private void saveAppointment(AppointmentVO vo, Doctor doctor, Patient patient) {

		Appointment appointment = new Appointment();
		BeanUtils.copyProperties(vo, appointment);
		appointment.setDoctorID(doctor);
		appointment.setPatientID(patient);
		appointment.setStatus("Booked");
		appointment.setCreatedBy("Admin");
		appointmentRepo.save(appointment);
	}

	@Override
	@Transactional
	public String bookAppointment(AppointmentVO vo) {

		validateAppointmentDate(vo.getAppointmentDate());

		// doctor row locks her
		Doctor doctor = validateDoctor(vo.getDoctorID());

		checkDoctorAvailability(doctor, vo.getAppointmentTime());

		checkSlotConflict(vo.getDoctorID(), vo.getAppointmentDate(), vo.getAppointmentTime());

		Patient patient = validatePatient(vo.getPatientID());

		saveAppointment(vo, doctor, patient);


		return "Appointment booked successfully";

	}

	@Override
	public AppointmentVO getAppointmentByIdvalue(Long id) {
		Appointment entity	=appointmentRepo.findById(id).orElseThrow(()->new IllegalArgumentException("invalid id number"));
		AppointmentVO vo = new AppointmentVO();
		BeanUtils.copyProperties(entity,vo);
		return vo;
	}

	@Override
	public List<AppointmentVO> getAllAppointments() {
		List<Appointment> listEntity =appointmentRepo.findAll();
		List<AppointmentVO> listVO = new ArrayList<AppointmentVO>();
		listEntity.forEach(entity->{
			AppointmentVO vo = new AppointmentVO();
			BeanUtils.copyProperties(entity, vo);
			listVO.add(vo);
		});
		return listVO;
	}

	@Override
	public String modifyAppointmentById(Long id, AppointmentVO new_vo) {
		//check first id is visible or not
		Appointment entity = appointmentRepo.findById(id).orElseThrow(()->new AppointmentIdNotFound("Appointment", "id", id));
		BeanUtils.copyProperties(new_vo, entity);
		return "Appointment updated id : "+id;
	}

	@Override
	public String removeAppointmentById(Long id) {
		//hard deletion
		Appointment entity = appointmentRepo.findById(id).orElseThrow(()->new AppointmentIdNotFound("Appointment", "id", id));
		appointmentRepo.deleteById(id);
		return "Appointment deleted id : "+id;
	}

	
}
