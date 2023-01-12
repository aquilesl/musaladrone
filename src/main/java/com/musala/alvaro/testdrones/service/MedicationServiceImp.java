package com.musala.alvaro.testdrones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.musala.alvaro.testdrones.model.Medication;
import com.musala.alvaro.testdrones.repository.MedicationRepository;

@Service
public class MedicationServiceImp implements IMedicationService {
	
	private MedicationRepository medRepo;

	@Autowired
	public MedicationServiceImp(MedicationRepository medRepo) {
		this.medRepo = medRepo;
	}

	@Override
	public List<Medication> getAllMedications() {
		// TODO Auto-generated method stub
		return medRepo.findAll();
	}

	@Override
	public Medication getMedicationById(long id) {
		// TODO Auto-generated method stub
		return medRepo.getReferenceById(id);
	}

	@Override
	public Medication createMedication(Medication medication) {
		// TODO Auto-generated method stub
		return medRepo.save(medication);
	}

	@Override
	public Medication updateMedication(long id, Medication newMedication) {
		// TODO Auto-generated method stub
		Medication oldMedication = medRepo.getReferenceById(id);
		oldMedication.setCode(newMedication.getCode());
		oldMedication.setImage(newMedication.getImage());
		oldMedication.setName(newMedication.getName());
		oldMedication.setWeight(newMedication.getWeight());
		return medRepo.save(oldMedication);
	}

	@Override
	public void deleteMedication(long id) {
		// TODO Auto-generated method stub
		medRepo.deleteById(id);
	}
	
	

}
