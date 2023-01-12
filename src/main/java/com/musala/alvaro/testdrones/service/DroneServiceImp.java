package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImp implements IDroneService {

	private final DroneRepository droneRepo;
	
	@Autowired
	public DroneServiceImp(DroneRepository droneRepo) {
		super();
		this.droneRepo = droneRepo;
	}

	@Override
	public List<Drone> getAllDrones() {
		// TODO Auto-generated method stub
		return droneRepo.findAll();
	}

	@Override
	public Drone getDroneById(long id) {
		// TODO Auto-generated method stub
		return droneRepo.getReferenceById(id);
	}

	@Override
	public Drone createDrone(Drone drone) {
		// TODO Auto-generated method stub
		return droneRepo.save(drone);
	}

	@Override
	public Drone updateDrone(long id, Drone newDrone) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		droneRepo.deleteById(id);
	}

}
