package com.hospital.doctor.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.doctor.entity.Doctor;
import com.hospital.doctor.exception.InvalidInputException;
import com.hospital.doctor.exception.ResourceNotFoundExcepiton;
import com.hospital.doctor.exception.SameDoctorAndSpecExists;
import com.hospital.doctor.repo.IDoctoRepository;
import com.hospital.doctor.vo.DoctorVO;

@Service
public class DoctorMgmtServiceImp implements IDoctorMgmtService {

	@Autowired
	private IDoctoRepository doctorRepo;
	
	public boolean DoctorExistsBySameNameandSpecilazation(String doctorName,String specilazation) {
		Boolean present = doctorRepo.existsByNameAndSpecialization(doctorName,specilazation);
		return present;
	}
	@Override
	
	public String createDoctor(DoctorVO vo) {
	
		Boolean present=DoctorExistsBySameNameandSpecilazation(vo.getName(), vo.getSpecialization());
		if(present) {
			throw new SameDoctorAndSpecExists("Doctor","Name & Specilazation",vo.getName()+vo.getSpecialization());
		}
		else {
		//validation 
		if(vo.getConsulationFee()<100) {
			throw new InvalidInputException("Consulation fee must be greater than 100");
		}
		if(vo.getAvailableFrom().isAfter(vo.getAvailableTo())) {
			throw new InvalidInputException("Available from time must be before Available To time");
		}		
		//now we need to convert the vo to entity
		Doctor entity = new Doctor();
		BeanUtils.copyProperties(vo, entity);
		entity.setCreatedBy(System.getProperty("user.name"));
		Long id=doctorRepo.save(entity).getDoctorId();
		return "Doctor has successfully registered with id - " + id;
		}
	}

	@Override
	public DoctorVO getDoctorById(Long id) {
		Doctor entity = doctorRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcepiton("Doctor","id",id));
		//convert entity to vo object
		DoctorVO vo = new DoctorVO();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<DoctorVO> getAllDoctors() {
		List<Doctor> listEntity = doctorRepo.findAll();
		List<DoctorVO> listVO = new ArrayList<DoctorVO>();
		//convert list entity to list vo object
		listEntity.forEach(entity->{
			DoctorVO vo = new DoctorVO();
			BeanUtils.copyProperties(entity, vo);
			listVO.add(vo);
		});
		return listVO;
	}

	@Override
	public String updateDoctorById(Long id, DoctorVO vo) {
		Doctor existingEntity = doctorRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcepiton("Doctor","id",id));
		//now check the exitsting entity and vo same or not
		boolean isSame = 
				existingEntity.getName().equalsIgnoreCase(vo.getName())&&
				existingEntity.getSpecialization().equalsIgnoreCase(vo.getSpecialization())&&
				existingEntity.getAvailableFrom().equals(vo.getAvailableFrom())&&
				existingEntity.getAvailableTo().equals(vo.getAvailableTo())&&
				existingEntity.getConsulationFee().equals(vo.getConsulationFee());
		if(isSame) {
			return "No changes detected. Doctor details remain unchanged.";
		}
		//update the values
		existingEntity.setName(vo.getName());
		existingEntity.setConsulationFee(vo.getConsulationFee());
		existingEntity.setSpecialization(vo.getSpecialization());
		existingEntity.setAvailableFrom(vo.getAvailableFrom());
		existingEntity.setAvailableTo(vo.getAvailableTo());
		existingEntity.setUpdatedBy(vo.getName());
		//save the object
		doctorRepo.save(existingEntity);
		return "Doctor with id :"+id+"has successfully got updated";
	}

	@Override
	public String deleteDoctor(Long id) {
		Doctor entity = doctorRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcepiton("Doctor","id",id));
		doctorRepo.delete(entity);
		return "Doctor with id "+id+" deleted from database";
	}

}
