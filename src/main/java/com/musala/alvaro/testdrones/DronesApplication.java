package com.musala.alvaro.testdrones;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.model.Medication;
import com.musala.alvaro.testdrones.model.Order;
import com.musala.alvaro.testdrones.model.Role;
import com.musala.alvaro.testdrones.model.User;
import com.musala.alvaro.testdrones.model.enums.DroneModel;
import com.musala.alvaro.testdrones.model.enums.DroneState;
import com.musala.alvaro.testdrones.model.enums.RoleType;
import com.musala.alvaro.testdrones.service.IDroneService;
import com.musala.alvaro.testdrones.service.IMedicationService;
import com.musala.alvaro.testdrones.service.IOrderService;
import com.musala.alvaro.testdrones.service.IRoleService;
import com.musala.alvaro.testdrones.service.IUserService;

@EnableScheduling
@SpringBootApplication
public class DronesApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	ApplicationRunner pupulateDataBase(IDroneService droneservice,
						   IMedicationService medicationservice,
						   IOrderService orderService,
						   IUserService userService,
						   IRoleService roleService) {
		return (ApplicationArguments args) ->  load(droneservice, medicationservice, orderService, userService, roleService);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DronesApplication.class, args);
	}


	public void load(IDroneService droneservice,
			   IMedicationService medicationservice,
			   IOrderService orderservice,IUserService userService,
			   IRoleService roleService){
		
		//Adding default admin user
		User adminUser = new User("admin","admin@gmail.com","$2a$10$yDbBp0aVZC/2WoCMkfcj3e/a42s.RWRflE5yoHLQaUUm0DT/mm/yO");
		Role role = new Role(RoleType.ROLE_ADMIN);
		Role temp = roleService.createRole(role);
		Set<Role> roles = Set.of(temp);
		adminUser.setRoles(roles);
		userService.createUser(adminUser);
		
		//adding drones to database...
		
		Drone d1 = new Drone("111AAA", DroneModel.HeavyWeight,400, 100, DroneState.Loaded);
		droneservice.createDrone(d1);
		Drone d2 = new Drone("222AAA", DroneModel.CruiserWeight,300, 100, DroneState.Loaded);
		droneservice.createDrone(d2);
		Drone d3 = new Drone("333AAA", DroneModel.LightWeight,100, 100, DroneState.Loaded);
		droneservice.createDrone(d3);
		Drone d4 = new Drone("444AAA", DroneModel.HeavyWeight,400, 100, DroneState.Idle);
		droneservice.createDrone(d4);
		Drone d5 = new Drone("555AAA", DroneModel.MiddleWeight,200, 100, DroneState.Idle);
		droneservice.createDrone(d5);
		Drone d6 = new Drone("666AAA", DroneModel.HeavyWeight,500, 100, DroneState.Idle);
		droneservice.createDrone(d6);
		Drone d7 = new Drone("777AAA", DroneModel.HeavyWeight,500, 20, DroneState.Idle);
		droneservice.createDrone(d7);
		
		//adding medication to database...
		
		Medication m1 = new Medication("Medicine_Musala-0001", 100, "ABC_123", "image1.png");
		medicationservice.createMedication(m1);
		Medication m2 = new Medication("Medicine_Musala-0002", 120, "ABC_234", "image2.png");
		medicationservice.createMedication(m2);
		Medication m3 = new Medication("Medicine_Musala-0003", 200, "ABC_345", "image3.png");
		medicationservice.createMedication(m3);
		Medication m4 = new Medication("Medicine_Musala-0004", 180, "ABC_456", "image4.png");
		medicationservice.createMedication(m4);
		Medication m5 = new Medication("Medicine_Musala-0005", 300, "ABC_567", "image5.png");
		medicationservice.createMedication(m5);
		Medication m6 = new Medication("Medicine_Musala-0006", 400, "ABC_678", "image6.png");
		medicationservice.createMedication(m6);
		Medication m7 = new Medication("Medicine_Musala-0007", 500, "ABC_789", "image7.png");
		medicationservice.createMedication(m7);
		
		//adding orders to database...

		Set<Medication> cargo1 = new HashSet<Medication>(Arrays.asList(m1,m2,m4)); 
		Order o1 = new Order(d1, cargo1);
		orderservice.createOrder(o1);
		
		Set<Medication> cargo2 = new HashSet<Medication>(Arrays.asList(m3)); 
		Order o2 = new Order(d2, cargo2);
		orderservice.createOrder(o2);
		
		Set<Medication> cargo3 = new HashSet<Medication>(Arrays.asList(m5)); 
		Order o3 = new Order(d3, cargo3);
		orderservice.createOrder(o3);
		
		System.out.println("DATABASE LOADED!!!");
		
	}
	
}
