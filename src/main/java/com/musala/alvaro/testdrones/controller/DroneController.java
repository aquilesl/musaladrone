package com.musala.alvaro.testdrones.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
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
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;
import com.musala.alvaro.testdrones.model.enums.DroneState;
import com.musala.alvaro.testdrones.service.IDroneService;

@RestController
@RequestMapping("/drones")
public class DroneController {

	private IDroneService droneService;
	
	private ModelMapper modelMapper;

	@Autowired
	public DroneController(IDroneService droneService, ModelMapper modelMapper) {
		this.droneService = droneService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/")
	public ResponseEntity<List<DroneDTO>> getAllDrones() {

			List<DroneDTO> drones =  droneService.getAllDrones().stream().map(drone -> modelMapper.map(drone, DroneDTO.class))
					.collect(Collectors.toList());

            if (drones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(drones, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
    public ResponseEntity<DroneDTO> getDroneById(@PathVariable("id") long id) {

       return new ResponseEntity<>(modelMapper.map(droneService.getDroneById(id), DroneDTO.class), HttpStatus.OK);

    }
	
	
	@PostMapping("/")
    public ResponseEntity<DroneDTO> addDrone(@Valid @RequestBody DroneDTO drone) {

            Drone droneRequest = modelMapper.map(drone, Drone.class); 
            droneRequest.setState(DroneState.Idle);
            Drone newDrone = droneService.createDrone(droneRequest);
            return new ResponseEntity<>(modelMapper.map(newDrone, DroneDTO.class), HttpStatus.CREATED);

    }
	
	@PutMapping("/{id}")
    public ResponseEntity<DroneDTO> updateDrone(@PathVariable("id") long id, @Valid @RequestBody DroneDTO drone) {

        	Drone newDrone = droneService.updateDrone(id,modelMapper.map(drone, Drone.class));
            return new ResponseEntity<>(modelMapper.map(newDrone, DroneDTO.class), HttpStatus.OK);

    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrone(@PathVariable("id") long id) {

            droneService.deleteDrone(id);
            return new ResponseEntity<>("Drone deleted", HttpStatus.OK);

    }

}
