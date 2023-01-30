package com.musala.alvaro.testdrones.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.musala.alvaro.testdrones.exceptions.LowBatteryException;
import com.musala.alvaro.testdrones.exceptions.NoMedicineToLoadException;
import com.musala.alvaro.testdrones.exceptions.NotAvailableDroneException;
import com.musala.alvaro.testdrones.exceptions.WeightLimitException;
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.model.GlobalConstant;
import com.musala.alvaro.testdrones.model.Medication;
import com.musala.alvaro.testdrones.model.Order;
import com.musala.alvaro.testdrones.model.DTO.BatteryCheckLogDTO;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;
import com.musala.alvaro.testdrones.model.DTO.MedicationDTO;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO;
import com.musala.alvaro.testdrones.model.enums.DroneState;
import com.musala.alvaro.testdrones.service.IBatteryCheckLogService;
import com.musala.alvaro.testdrones.service.IDroneService;
import com.musala.alvaro.testdrones.service.IOrderService;

@RestController
@RequestMapping("/fleet")
public class FleetController {
	
	private IDroneService droneService;
	private IOrderService orderService;
	private ModelMapper modelMapper;
	private IBatteryCheckLogService battService;
	
	
	@Autowired
	public FleetController(IDroneService droneService, ModelMapper modelMapper, 
			IOrderService orderService, IBatteryCheckLogService battService) {
		this.droneService = droneService;
		this.modelMapper = modelMapper;
		this.orderService = orderService;
		this.battService = battService;
	}



//	- registering a drone;
	
	@PostMapping("/register-drone")
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO drone) {

            Drone droneRequest = modelMapper.map(drone, Drone.class); 
            droneRequest.setState(DroneState.Idle);
            Drone newDrone = droneService.createDrone(droneRequest);
            return new ResponseEntity<>(modelMapper.map(newDrone, DroneDTO.class), HttpStatus.CREATED);

    }
	
//	- loading a drone with medication items;
	
	@PostMapping("/load-drone")
    public ResponseEntity<String> registerOrder(@Valid @RequestBody OrderDTO order) throws NotAvailableDroneException, LowBatteryException, NoMedicineToLoadException, WeightLimitException {
		
			
			Drone drone = droneService.getDroneById(order.getDrone().getId());
			Set<MedicationDTO> medications = order.getMedications();
				
			if(drone.getState() != DroneState.Idle) {
				throw new NotAvailableDroneException();
			}
			
			if(drone.getBatteryCapacity() < GlobalConstant.MIN_WARNING_LEVEL_BATTERY) {
				throw new LowBatteryException();
			}
			
			drone.setState(DroneState.Loading);
			drone = droneService.updateDrone(drone.getId(), drone);
			
			Set<Medication> med = medications.stream()
					.map(medTemp -> modelMapper.map(medTemp, Medication.class))
					.collect(Collectors.toSet());
			
			if(med.isEmpty()) {
				drone.setState(DroneState.Idle);
				drone = droneService.updateDrone(drone.getId(), drone);
				
				throw new NoMedicineToLoadException();
			}
			
			double weight = 0;
			
			for (Medication medication : med) {
				weight+= medication.getWeight();
			}
			
			if(weight > drone.getWeightLimit()) {
				drone.setState(DroneState.Idle);
				drone = droneService.updateDrone(drone.getId(), drone);
				
				throw new WeightLimitException();
			}
			
			drone.setState(DroneState.Loaded);
			Order tempOrder = new Order(drone, med);
			orderService.createOrder(tempOrder);
			
			return new ResponseEntity<>("The drone has been loaded.", HttpStatus.OK);
		
		
	}
	
//	- checking loaded medication items for a given drone;
	
	@GetMapping("/drone-cargo/{id}")
    public ResponseEntity<OrderDTO> showCargo(@PathVariable("id") long droneId){
		
		Order order = orderService.findFirstByDroneIdAndDroneState(droneId, DroneState.Loaded);
		OrderDTO cargo = modelMapper.map(order, OrderDTO.class);
		return new ResponseEntity<>(cargo, HttpStatus.OK);
	}
	
//	- checking available drones for loading;
	
	@GetMapping("/available-drones")
	public ResponseEntity<List<DroneDTO>> getAvailableDrones(){
		List<Drone> drones = droneService.findByState(DroneState.Idle);
		
		if(drones.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		List<DroneDTO> dronesdto = drones.stream()
				.map(drone -> modelMapper.map(drone, DroneDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(dronesdto, HttpStatus.OK);
	}
	
//	- check drone battery level for a given drone;

	@GetMapping("/drone-battery-level/{id}")
	public ResponseEntity<Integer> getCheckBatteryLevel(@PathVariable("id") long droneId){
		
		Drone drone = droneService.getDroneById(droneId);
        return new ResponseEntity<>(drone.getBatteryCapacity(), HttpStatus.OK);
        
	}
	
	@GetMapping("battery-check-log")
	public ResponseEntity<List<BatteryCheckLogDTO>> getBatteryCheckLog(){

			List<BatteryCheckLogDTO> log =  battService.getAllBatteryCheckLog().stream()
					.map(logTemp -> modelMapper.map(logTemp, BatteryCheckLogDTO.class))
					.collect(Collectors.toList());

            if (log.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(log, HttpStatus.OK);
		
	}

}
