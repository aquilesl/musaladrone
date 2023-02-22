package com.musala.alvaro.testdrones.business;

import java.util.List;
import com.musala.alvaro.testdrones.model.DTO.MedicationDTO;

public interface IMedicationBusiness {

	public List<MedicationDTO> getAllMedications();
	public MedicationDTO getMedicationById(Long medicationId);
	public MedicationDTO addMedication(MedicationDTO medication);
	public MedicationDTO updateMedication(Long medicationId, MedicationDTO medication);
	public void deleteMedication(Long medicationId);
	
}
