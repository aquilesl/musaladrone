package com.musala.alvaro.testdrones.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.musala.alvaro.testdrones.model.Medication;
import com.musala.alvaro.testdrones.model.DTO.MedicationDTO;
import com.musala.alvaro.testdrones.service.IMedicationService;

@Service
public class MedicationBusinessImp implements IMedicationBusiness{

	private IMedicationService medicService;
	private ModelMapper modelMapper;
	
	public MedicationBusinessImp(IMedicationService medicService, ModelMapper modelMapper) {
		this.medicService = medicService;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<MedicationDTO> getAllMedications() {
		List<MedicationDTO> medications =  medicService.getAllMedications().stream()
				.map(medication -> modelMapper.map(medication, MedicationDTO.class))
				.collect(Collectors.toList());

        if (medications.isEmpty()) {
            return new ArrayList<MedicationDTO>();
        }
        return medications;
	}

	@Override
	public MedicationDTO getMedicationById(Long medicationId) {
		return modelMapper.map(medicService.getMedicationById(medicationId), MedicationDTO.class);
	}

	@Override
	public MedicationDTO addMedication(MedicationDTO medication) {
		Medication medicationRequest = modelMapper.map(medication, Medication.class); 
        Medication newMedication = medicService.createMedication(medicationRequest);
        return modelMapper.map(newMedication, MedicationDTO.class);
	}

	@Override
	public MedicationDTO updateMedication(Long medicationId, MedicationDTO medication) {
		Medication newMedication = medicService.updateMedication(medicationId,modelMapper.map(medication, Medication.class));
        return modelMapper.map(newMedication, MedicationDTO.class);
	}

	@Override
	public void deleteMedication(Long medicationId) {
		medicService.deleteMedication(medicationId);
	}

}
