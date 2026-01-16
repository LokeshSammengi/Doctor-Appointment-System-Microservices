package com.hospital.patient.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.patient.entity.Patient;
import com.hospital.patient.exception.ResourceNotFoundException;
import com.hospital.patient.repoistory.IPatientRepository;
import com.hospital.patient.vo.PatientVO;

@Service
public class PatientMgmtServiceImp implements IPatientMgmtService {

	@Autowired
	private IPatientRepository patientRepo;

	@Override
	public String createPatient(PatientVO vo) {
		// now we need to convert vo into entity
		Patient Entity = new Patient();
		// copy the properties
		BeanUtils.copyProperties(vo, Entity);
		Entity.setCreatedBy(System.getProperty("user.name"));
		// use the repo to save the object
		Long id = patientRepo.save(Entity).getPatientID();
		
		return "Patient has successfully registered with id - " + id;
	}

	@Override
	public PatientVO getPatientById(Long id) {
		// Here we need to convert entity to vo
		Patient entity = patientRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("patient", "id", id));
		// convert the entity to vo
		PatientVO vo = new PatientVO();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<PatientVO> getAllPatients() {
		//Now we need to convert List<entity> to List<vo>
		List<Patient> listEntity = patientRepo.findAll();
		//convert the list entity to list vo 
		List<PatientVO> listVO = new ArrayList<PatientVO>();
		listEntity.forEach(entity->{
			PatientVO vo = new PatientVO();
			BeanUtils.copyProperties(entity, vo);
			listVO.add(vo);			
		});
		return listVO;
	}

	@Override
	public String updatePatient(Long id, PatientVO vo) {
		//NOW we need to vo to entity 
		Patient existingEntity = patientRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient","id", id));
		//checking the existing content and vo content 
		boolean isSame = 
				existingEntity.getName().equalsIgnoreCase(vo.getName())&&
				existingEntity.getGender().equalsIgnoreCase(vo.getGender())&&
				existingEntity.getAge().equals(vo.getAge())&&
				existingEntity.getEmail().equals(vo.getEmail())&&
				existingEntity.getPhoneNumber().equals(vo.getPhoneNumber());
		if(isSame) {
			return "No changes detected. Patient details remain unchanged.";
		}
		
//		updating the entity class fields
		existingEntity.setName(vo.getName());
		existingEntity.setAge(vo.getAge());
		existingEntity.setGender(vo.getGender());
		existingEntity.setPhoneNumber(vo.getPhoneNumber());
		existingEntity.setEmail(vo.getEmail());
		existingEntity.setUpdatedBy(existingEntity.getName());
		//save the entity object
		patientRepo.save(existingEntity);
		return "Patient with id :"+id+"has successfully got updated";
	}

	@Override
	public String deletePatientById(Long id) {
		//now i am performing hard deletion
		Patient entity=patientRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient","id", id));
		patientRepo.delete(entity);
		return "Patient with id "+id+" deleted from database";
	}

}
