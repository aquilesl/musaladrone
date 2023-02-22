package com.musala.alvaro.testdrones.controller;

import java.util.List;
import javax.validation.Valid;
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
import org.springframework.beans.factory.annotation.Autowired;
import com.musala.alvaro.testdrones.business.IDroneBusiness;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;

@RestController
@RequestMapping("/drones")
public class DroneController {

	IDroneBusiness droneBusiness;

	@Autowired
	public DroneController(IDroneBusiness droneBusiness) {
		this.droneBusiness = droneBusiness;
	}

	@GetMapping("/")
	public ResponseEntity<List<DroneDTO>> getAllDrones() {

            return new ResponseEntity<>(droneBusiness.getAllDrones(), HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
    public ResponseEntity<DroneDTO> getDroneById(@PathVariable("id") long id) {

       return new ResponseEntity<>(droneBusiness.getDroneById(id), HttpStatus.OK);

    }
	
	
	@PostMapping("/")
    public ResponseEntity<DroneDTO> addDrone(@Valid @RequestBody DroneDTO drone) {

            DroneDTO result = droneBusiness.addDrone(drone);
            return new ResponseEntity<>(result, HttpStatus.CREATED);

    }
	
	@PutMapping("/{id}")
    public ResponseEntity<DroneDTO> updateDrone(@PathVariable("id") long id, @Valid @RequestBody DroneDTO drone) {

        	DroneDTO newDrone = droneBusiness.updateDrone(id, drone);
            return new ResponseEntity<>(newDrone, HttpStatus.OK);

    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrone(@PathVariable("id") long id) {

            droneBusiness.deleteDrone(id);
            return new ResponseEntity<>("Drone deleted", HttpStatus.OK);

    }

}
