package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.Medication;

public interface IMedicationService {

	List<Medication> getAllMedications();
	Medication getMedicationById(long id);
	Medication createMedication(Medication medication);
	Medication updateMedication(long id, Medication medication);
	void deleteMedication(long id);
	
}
