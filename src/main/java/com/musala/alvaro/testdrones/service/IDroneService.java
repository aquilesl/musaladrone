package com.musala.alvaro.testdrones.service;

import java.util.List;

import com.musala.alvaro.testdrones.model.Drone;

public interface IDroneService {
	
	List<Drone> getAllDrones();
	Drone getDroneById(long id);
	Drone createDrone(Drone drone);
	Drone updateDrone(long id, Drone drone);
	void deleteDrone(long id);
	
}
