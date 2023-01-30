package com.musala.alvaro.testdrones.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.musala.alvaro.testdrones.model.Medication;
import com.musala.alvaro.testdrones.model.DTO.MedicationDTO;
import com.musala.alvaro.testdrones.service.IMedicationService;

@RestController
@RequestMapping("/medication")
public class MedicationController {

private IMedicationService medicService;
	
	private ModelMapper modelMapper;

	@Autowired
	public MedicationController(IMedicationService medicService, ModelMapper modelMapper) {
		this.medicService = medicService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public ResponseEntity<List<MedicationDTO>> getAllMedications() {

			List<MedicationDTO> medications =  medicService.getAllMedications().stream()
					.map(medication -> modelMapper.map(medication, MedicationDTO.class))
					.collect(Collectors.toList());

            if (medications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medications, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
    public ResponseEntity<MedicationDTO> getMedicationById(@PathVariable("id") long id) {
        return new ResponseEntity<>(modelMapper.map(medicService.getMedicationById(id), MedicationDTO.class), HttpStatus.OK);

    }
	
	
	@PostMapping
    public ResponseEntity<MedicationDTO> addMedication(@Valid @RequestBody MedicationDTO medication) {

            Medication medicationRequest = modelMapper.map(medication, Medication.class); 
            Medication newMedication = medicService.createMedication(medicationRequest);
            return new ResponseEntity<>(modelMapper.map(newMedication, MedicationDTO.class), HttpStatus.CREATED);

    }
	
	@PutMapping("/{id}")
    public ResponseEntity<MedicationDTO> updateMedication(@PathVariable("id") long id, @Valid @RequestBody MedicationDTO medication) {

        Medication newMedication = medicService.updateMedication(id,modelMapper.map(medication, Medication.class));
        return new ResponseEntity<>(modelMapper.map(newMedication, MedicationDTO.class), HttpStatus.OK);

    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedication(@PathVariable("id") long id) {

        	medicService.deleteMedication(id);
            return new ResponseEntity<>("Medicine deleted.", HttpStatus.OK);

    }
	
}
