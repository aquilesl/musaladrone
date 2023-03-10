package com.musala.alvaro.testdrones.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.alvaro.testdrones.model.Order;
import com.musala.alvaro.testdrones.model.enums.DroneState;

public interface OrderRepository extends JpaRepository<Order, Long>{

    Order findFirstByDroneIdAndDroneState (long droneId, DroneState state);
	
}
