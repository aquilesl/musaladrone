package com.musala.alvaro.testdrones.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO;
import com.musala.alvaro.testdrones.model.enums.DroneModel;
import com.musala.alvaro.testdrones.model.enums.DroneState;

@SpringBootTest
class FleetControllerTest {
	
	//Two quick tests.......
	
	/*@Autowired
    FleetController fleetController;
	
	@Test
	void testRegisterDrone() {

		DroneDTO data1 = new DroneDTO("888AAA", DroneModel.HeavyWeight, 500, 100, DroneState.Idle); 
		ResponseEntity<DroneDTO> responseEntity = fleetController.registerDrone(data1);
		System.out.println(responseEntity.getBody());
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(data1.getModel(), responseEntity.getBody().getModel());
		assertEquals(data1.getBatteryCapacity(), responseEntity.getBody().getBatteryCapacity());
		assertEquals(data1.getSerialNumber(), responseEntity.getBody().getSerialNumber());
		assertEquals(data1.getState(), responseEntity.getBody().getState());
		assertEquals(data1.getWeightLimit(), responseEntity.getBody().getWeightLimit());
		
	}


	@Test
	void testShowCargo() {
		ResponseEntity<OrderDTO> data = fleetController.showCargo(1);
		assertEquals(data.getBody().getDrone().getId(),1,"OK");
	}*/

}
