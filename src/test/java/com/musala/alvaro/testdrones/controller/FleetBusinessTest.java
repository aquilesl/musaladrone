package com.musala.alvaro.testdrones.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import com.musala.alvaro.testdrones.business.FleetBusinessImp;
import com.musala.alvaro.testdrones.business.IFleetBusiness;
import com.musala.alvaro.testdrones.model.BatteryCheckLog;
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.model.Medication;
import com.musala.alvaro.testdrones.model.Order;
import com.musala.alvaro.testdrones.model.DTO.BatteryCheckLogDTO;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO;
import com.musala.alvaro.testdrones.model.enums.DroneModel;
import com.musala.alvaro.testdrones.model.enums.DroneState;
import com.musala.alvaro.testdrones.service.BatteryCheckLogServiceImp;
import com.musala.alvaro.testdrones.service.DroneServiceImp;
import com.musala.alvaro.testdrones.service.IBatteryCheckLogService;
import com.musala.alvaro.testdrones.service.IDroneService;
import com.musala.alvaro.testdrones.service.IOrderService;
import com.musala.alvaro.testdrones.service.OrderServiceImp;

class FleetBusinessTest {
	

	//Drone Fleet
	 Drone d1 = new Drone("111AAA", DroneModel.HeavyWeight,400, 100, DroneState.Loaded);
	 Drone d2 = new Drone("222AAA", DroneModel.CruiserWeight,300, 20, DroneState.Loaded);
	 Drone d3 = new Drone("333AAA", DroneModel.LightWeight,100, 1, DroneState.Loaded);
	 Drone d4 = new Drone("444AAA", DroneModel.HeavyWeight,400, 60, DroneState.Idle);
	 List<Drone> dronelist = new ArrayList<Drone>(Arrays.asList(new Drone[]{d1,d2,d3,d4}));
	
	//Medications
	 Medication m1 = new Medication("Medicine_Musala-0001", 100, "ABC_123", "image1.png");
	 Medication m2 = new Medication("Medicine_Musala-0002", 120, "ABC_234", "image2.png");
	 Medication m3 = new Medication("Medicine_Musala-0003", 200, "ABC_345", "image3.png");
	 Medication m4 = new Medication("Medicine_Musala-0004", 500, "ABC_456", "image4.png");
	
	//Orders
	 Set<Medication> cargo1 = new HashSet<Medication>(Arrays.asList(m1,m2,m4)); 
	 Order o1 = new Order(d1, cargo1);
	 Set<Medication> cargo2 = new HashSet<Medication>(Arrays.asList(m3)); 
	 Order o2 = new Order(d2, cargo2);
	 Set<Medication> cargo3 = new HashSet<Medication>(Arrays.asList(m4)); 
	 Order o3 = new Order(d3, cargo3);
	 List<Order> orderlist = new ArrayList<Order>(Arrays.asList(new Order[]{o1,o2,o3}));
	
	//BatteryCheckLog
	 BatteryCheckLog bcl1 = new BatteryCheckLog(d1, new Date(), d1.getBatteryCapacity());
	 BatteryCheckLog bcl2 = new BatteryCheckLog(d2, new Date(), d2.getBatteryCapacity());
	 BatteryCheckLog bcl3 = new BatteryCheckLog(d3, new Date(), d3.getBatteryCapacity());
	 BatteryCheckLog bcl4 = new BatteryCheckLog(d4, new Date(), d4.getBatteryCapacity());
	 List<BatteryCheckLog> listLog = new ArrayList<BatteryCheckLog>(Arrays.asList(new BatteryCheckLog[]{bcl1,bcl2,bcl3,bcl4}));
	
	 IFleetBusiness fleetBusiness;
	 ModelMapper modelMapper = new ModelMapper();
	
	@BeforeEach
	void beforeEach() {

		//Mocking Objects
		DroneServiceImp dronImp= Mockito.mock(DroneServiceImp.class);
		IDroneService droneService = dronImp;
		
		OrderServiceImp orderImp = Mockito.mock(OrderServiceImp.class);
		IOrderService orderService = orderImp;
		
		BatteryCheckLogServiceImp battCheckImp = Mockito.mock(BatteryCheckLogServiceImp.class);
		IBatteryCheckLogService battService = battCheckImp;

		fleetBusiness = new FleetBusinessImp(droneService, orderService, modelMapper, battService);
		
		//Data Access Object for Drone Entity
		Mockito.when(droneService.getDroneById(1L)).thenReturn(d1);
		Mockito.when(droneService.getDroneById(2L)).thenReturn(d2);
		Mockito.when(droneService.getDroneById(3L)).thenReturn(d3);
		Mockito.when(droneService.getDroneById(4L)).thenReturn(d4);
		Mockito.when(droneService.getAllDrones()).thenReturn(dronelist);
		
		//Data Access Object for Medication Entity
		Mockito.when(battService.getBatteryCheckLogById(1L)).thenReturn(bcl1);
		Mockito.when(battService.getBatteryCheckLogById(2L)).thenReturn(bcl2);
		Mockito.when(battService.getBatteryCheckLogById(3L)).thenReturn(bcl3);
		Mockito.when(battService.getBatteryCheckLogById(4L)).thenReturn(bcl4);
		Mockito.when(battService.getAllBatteryCheckLog()).thenReturn(listLog);
		
		//Data Access Object for Order Entity
		Mockito.when(orderService.getOrderById(1L)).thenReturn(o1);
		Mockito.when(orderService.getOrderById(2L)).thenReturn(o2);
		Mockito.when(orderService.getOrderById(3L)).thenReturn(o3);
		Mockito.when(orderService.getAllOrders()).thenReturn(orderlist);
		
		//orderService.findFirstByDroneIdAndDroneState
		Mockito.when(orderService.findFirstByDroneIdAndDroneState(1L, DroneState.Loaded)).thenReturn(o1);
		Mockito.when(orderService.findFirstByDroneIdAndDroneState(2L, DroneState.Loaded)).thenReturn(o2);
		Mockito.when(orderService.findFirstByDroneIdAndDroneState(3L, DroneState.Loaded)).thenReturn(o3);
		//droneService.findByState
		Mockito.when(droneService.findByState(DroneState.Idle)).thenReturn(new ArrayList<Drone>(Arrays.asList(new Drone[]{d4})));
		//battService.getAllBatteryCheckLog()
		Mockito.when(battService.getAllBatteryCheckLog()).thenReturn(listLog);
	}
	
	@Test
	@DisplayName("Testing checkBatteryLevel() method")
	void checkBatteryLevelTest() {
		int result = fleetBusiness.getCheckBatteryLevel(1L);
		int expected = 100;
		 assertEquals(expected,result, "Done");
	}
	

	@Test
	@DisplayName("Testing showCargo() Method")
	void testShowCargo() {
		OrderDTO result = fleetBusiness.showCargo(1L);
		OrderDTO expected = modelMapper.map(o1, OrderDTO.class);
		assertEquals(expected,result,"Done");
	}
	
	@Test
	@DisplayName("Testing getAvailableDrones() Method")
	void getAvailableDrones() {
		List<DroneDTO> result = fleetBusiness.getAvailableDrones();
		List<DroneDTO> expected = (new ArrayList<Drone>(Arrays.asList(new Drone[]{d4}))).stream()
				.map(drone -> modelMapper.map(drone, DroneDTO.class))
				.collect(Collectors.toList());
		assertEquals(expected,result,"Done");
	}
	
	@Test
	@DisplayName("Testing getBatteryCheckLog() Method")
	void getBatteryCheckLog() {
		List<BatteryCheckLogDTO> result = fleetBusiness.getBatteryCheckLog();
		List<BatteryCheckLogDTO> expected = listLog.stream()
				.map(checkLog -> modelMapper.map(checkLog, BatteryCheckLogDTO.class))
				.collect(Collectors.toList());
		assertEquals(expected,result,"Done");
	}

}
