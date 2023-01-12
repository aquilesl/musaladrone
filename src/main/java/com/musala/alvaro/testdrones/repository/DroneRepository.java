package com.musala.alvaro.testdrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.alvaro.testdrones.model.Drone;

public interface DroneRepository extends JpaRepository<Drone, Long> {

}
