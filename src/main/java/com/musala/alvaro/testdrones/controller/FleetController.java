package com.musala.alvaro.testdrones.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.musala.alvaro.testdrones.business.IFleetBusiness;
import com.musala.alvaro.testdrones.exceptions.LowBatteryException;
import com.musala.alvaro.testdrones.exceptions.NoMedicineToLoadException;
import com.musala.alvaro.testdrones.exceptions.NotAvailableDroneException;
import com.musala.alvaro.testdrones.exceptions.WeightLimitException;
import com.musala.alvaro.testdrones.model.DTO.BatteryCheckLogDTO;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/fleet")
public class FleetController {

	private IFleetBusiness fleetBusiness;
	
	
	public FleetController(IFleetBusiness fleetBusiness) {
		this.fleetBusiness = fleetBusiness;

	}


//	- registering a drone;
	
	@PostMapping("/register-drone")
	@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO drone) {

            DroneDTO dronedto = fleetBusiness.registerDrone(drone);
            return new ResponseEntity<>(dronedto, HttpStatus.CREATED);

    }
	
//	- loading a drone with medication items;
	
	@PostMapping("/load-drone")
	@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> registerOrder(@Valid @RequestBody OrderDTO order) throws NotAvailableDroneException, LowBatteryException, NoMedicineToLoadException, WeightLimitException {
		
			fleetBusiness.registerOrder(order);
			return new ResponseEntity<>("The drone has been loaded.", HttpStatus.OK);

	}
	
//	- checking loaded medication items for a given drone;
	
	@GetMapping("/drone-cargo/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderDTO> showCargo(@PathVariable("id") long droneId){

		OrderDTO cargo = fleetBusiness.showCargo(droneId);
		return new ResponseEntity<>(cargo, HttpStatus.OK);
	}
	
//	- checking available drones for loading;
	
	@GetMapping("/available-drones")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<DroneDTO>> getAvailableDrones(){

		List<DroneDTO> dronesdto = fleetBusiness.getAvailableDrones();
		return new ResponseEntity<>(dronesdto, HttpStatus.OK);
	}
	
//	- check drone battery level for a given drone;

	@GetMapping("/drone-battery-level/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Integer> getCheckBatteryLevel(@PathVariable("id") long droneId){

		return new ResponseEntity<>(fleetBusiness.getCheckBatteryLevel(droneId), HttpStatus.OK);
        
	}
	
	@GetMapping("battery-check-log")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<BatteryCheckLogDTO>> getBatteryCheckLog(){

			List<BatteryCheckLogDTO> log =  fleetBusiness.getBatteryCheckLog();
            return new ResponseEntity<>(log, HttpStatus.OK);
		
	}

}
