package com.musala.alvaro.testdrones.business;

import java.util.List;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;

public interface IDroneBusiness {

	public List<DroneDTO> getAllDrones();
	public DroneDTO getDroneById(long droneId);
	public DroneDTO addDrone(DroneDTO drone);
	public DroneDTO updateDrone(Long id, DroneDTO drone);
	public void deleteDrone(Long id);
	
}
