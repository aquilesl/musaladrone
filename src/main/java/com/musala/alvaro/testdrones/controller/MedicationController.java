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

import com.musala.alvaro.testdrones.business.IMedicationBusiness;
import com.musala.alvaro.testdrones.model.Medication;
import com.musala.alvaro.testdrones.model.DTO.MedicationDTO;
import com.musala.alvaro.testdrones.service.IMedicationService;

@RestController
@RequestMapping("/medication")
public class MedicationController {

	private IMedicationBusiness medicationBusinesss;

	@Autowired
	public MedicationController(IMedicationBusiness medicationBusinesss) {
		this.medicationBusinesss = medicationBusinesss;
	}

	@GetMapping
	public ResponseEntity<List<MedicationDTO>> getAllMedications() {

			List<MedicationDTO> medications =  medicationBusinesss.getAllMedications();
            return new ResponseEntity<>(medications, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
    public ResponseEntity<MedicationDTO> getMedicationById(@PathVariable("id") long id) {
        return new ResponseEntity<>(medicationBusinesss.getMedicationById(id), HttpStatus.OK);

    }
	
	
	@PostMapping
    public ResponseEntity<MedicationDTO> addMedication(@Valid @RequestBody MedicationDTO medication) {

            return new ResponseEntity<>(medicationBusinesss.addMedication(medication), HttpStatus.CREATED);

    }
	
	@PutMapping("/{id}")
    public ResponseEntity<MedicationDTO> updateMedication(@PathVariable("id") long id, @Valid @RequestBody MedicationDTO medication) {

        return new ResponseEntity<>(medicationBusinesss.updateMedication(id, medication), HttpStatus.OK);

    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedication(@PathVariable("id") long id) {

			medicationBusinesss.deleteMedication(id);
            return new ResponseEntity<>("Medicine deleted.", HttpStatus.OK);

    }
	
}
