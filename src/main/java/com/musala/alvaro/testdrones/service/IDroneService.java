package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.model.enums.DroneState;

public interface IDroneService {
	
	List<Drone> getAllDrones();
	Drone getDroneById(long id);
	Drone createDrone(Drone drone);
	Drone updateDrone(long id, Drone drone);
	void deleteDrone(long id);
	List<Drone> findByState(DroneState state);
	
}
