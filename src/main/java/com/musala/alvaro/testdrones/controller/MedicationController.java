package com.musala.alvaro.testdrones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
		super();
		this.medicService = medicService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public ResponseEntity<List<MedicationDTO>> getAllMedications() {
		try {

			List<MedicationDTO> medications =  medicService.getAllMedications().stream()
					.map(medication -> modelMapper.map(medication, MedicationDTO.class))
					.collect(Collectors.toList());

            if (medications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<MedicationDTO> getMedicationById(@PathVariable("id") long id) {
        Optional<Medication> medication = Optional.ofNullable(medicService.getMedicationById(id));
        if (medication.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(medication.get(), MedicationDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	@PostMapping
    public ResponseEntity<MedicationDTO> addMedication(@Valid @RequestBody MedicationDTO medication) {
        try {

            Medication medicationRequest = modelMapper.map(medication, Medication.class); 
            Medication newMedication = medicService.createMedication(medicationRequest);
            return new ResponseEntity<>(modelMapper.map(newMedication, MedicationDTO.class), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<MedicationDTO> updateMedication(@PathVariable("id") long id, @Valid @RequestBody MedicationDTO medication) {
        Optional<Medication> oldMedication = Optional.ofNullable(medicService.getMedicationById(id));
        if (oldMedication.isPresent()) {
        	Medication newMedication = medicService.updateMedication(id,modelMapper.map(medication, Medication.class));
            return new ResponseEntity<>(modelMapper.map(newMedication, MedicationDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedication(@PathVariable("id") long id) {
        try {
            medicService.deleteMedication(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}
