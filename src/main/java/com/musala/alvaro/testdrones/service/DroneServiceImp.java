package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.model.enums.DroneState;
import com.musala.alvaro.testdrones.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImp implements IDroneService {

	private DroneRepository droneRepo;
	
	@Autowired
	public DroneServiceImp(DroneRepository droneRepo) {
		this.droneRepo = droneRepo;
	}

	@Override
	public List<Drone> getAllDrones() {
		return droneRepo.findAll();
	}

	@Override
	public Drone getDroneById(long id) {
		return droneRepo.getReferenceById(id);
	}

	@Override
	public Drone createDrone(Drone drone) {
		return droneRepo.save(drone);
	}

	@Override
	public Drone updateDrone(long id, Drone newDrone) {

		Drone oldDrone = droneRepo.getReferenceById(id);
		oldDrone.setBatteryCapacity(newDrone.getBatteryCapacity());
		oldDrone.setModel(newDrone.getModel());
		oldDrone.setSerialNumber(newDrone.getSerialNumber());
		oldDrone.setState(newDrone.getState());
		oldDrone.setWeightLimit(newDrone.getWeightLimit());
		
		return droneRepo.save(oldDrone);
	}

	@Override
	public void deleteDrone(long id) {
		droneRepo.deleteById(id);
	}

	@Override
	public List<Drone> findByState(DroneState state) {
		return droneRepo.findByState(state);
	}

}
