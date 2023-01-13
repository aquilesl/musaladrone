package com.musala.alvaro.testdrones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.validation.FieldError;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		try {

			List<DroneDTO> drones =  droneService.getAllDrones().stream().map(drone -> modelMapper.map(drone, DroneDTO.class))
					.collect(Collectors.toList());

            if (drones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(drones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<DroneDTO> getDroneById(@PathVariable("id") long id) {
        Optional<Drone> drone = Optional.ofNullable(droneService.getDroneById(id));
        if (drone.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(drone.get(), DroneDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	@PostMapping("/")
    public ResponseEntity<DroneDTO> addDrone(@Valid @RequestBody DroneDTO drone) {
        try {

            Drone droneRequest = modelMapper.map(drone, Drone.class); 
            droneRequest.setState(DroneState.Idle);
            Drone newDrone = droneService.createDrone(droneRequest);
            return new ResponseEntity<>(modelMapper.map(newDrone, DroneDTO.class), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<DroneDTO> updateDrone(@PathVariable("id") long id, @Valid @RequestBody DroneDTO drone) {
        Optional<Drone> oldDrone = Optional.ofNullable(droneService.getDroneById(id));
        if (oldDrone.isPresent()) {
        	Drone newDrone = droneService.updateDrone(id,modelMapper.map(drone, Drone.class));
            return new ResponseEntity<>(modelMapper.map(newDrone, DroneDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDrone(@PathVariable("id") long id) {
        try {
            droneService.deleteDrone(id);
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
