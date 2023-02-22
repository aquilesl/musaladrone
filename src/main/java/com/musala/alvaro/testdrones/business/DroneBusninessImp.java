package com.musala.alvaro.testdrones.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;
import com.musala.alvaro.testdrones.model.enums.DroneState;
import com.musala.alvaro.testdrones.service.IDroneService;

@Service
public class DroneBusninessImp implements IDroneBusiness{

	private IDroneService droneService;
	private ModelMapper modelMapper;
	
	public DroneBusninessImp(IDroneService droneService, ModelMapper modelMapper) {
		this.droneService = droneService;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<DroneDTO> getAllDrones() {
		List<DroneDTO> drones =  droneService.getAllDrones().stream().map(drone -> modelMapper.map(drone, DroneDTO.class))
				.collect(Collectors.toList());

        if (drones.isEmpty()) {
            return new ArrayList<DroneDTO>();
        }

		return drones;
	}

	@Override
	public DroneDTO getDroneById(long droneId) {
		return modelMapper.map(droneService.getDroneById(droneId), DroneDTO.class);
	}

	@Override
	public DroneDTO addDrone(DroneDTO drone) {
		Drone droneRequest = modelMapper.map(drone, Drone.class); 
        droneRequest.setState(DroneState.Idle);
        Drone newDrone = droneService.createDrone(droneRequest);
        return modelMapper.map(newDrone, DroneDTO.class);
	}

	@Override
	public DroneDTO updateDrone(Long droneId, DroneDTO drone) {
		Drone newDrone = droneService.updateDrone(droneId,modelMapper.map(drone, Drone.class));
        return modelMapper.map(newDrone, DroneDTO.class);
	}

	@Override
	public void deleteDrone(Long droneId) {
		droneService.deleteDrone(droneId);
	}

}
