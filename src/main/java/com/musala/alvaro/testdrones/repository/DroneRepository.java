package com.musala.alvaro.testdrones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.model.enums.DroneState;

public interface DroneRepository extends JpaRepository<Drone, Long> {

	List<Drone> findByState(DroneState state);
	
}
