package com.musala.alvaro.testdrones.business;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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

@Service
public class FleetBusinessImp implements IFleetBusiness{

	private IDroneService droneService;
	private IOrderService orderService;
	private ModelMapper modelMapper;
	private IBatteryCheckLogService battService;
	
	public FleetBusinessImp(IDroneService droneService, IOrderService orderService, ModelMapper modelMapper,
			IBatteryCheckLogService battService) {
		super();
		this.droneService = droneService;
		this.orderService = orderService;
		this.modelMapper = modelMapper;
		this.battService = battService;
	}

	@Override
	public List<DroneDTO> getAvailableDrones() {
		List<Drone> drones = droneService.findByState(DroneState.Idle);
		
		if(drones.isEmpty()) {
			return new LinkedList<DroneDTO>();
		}
		
		List<DroneDTO> dronesdto = drones.stream()
				.map(drone -> modelMapper.map(drone, DroneDTO.class))
				.collect(Collectors.toList());
		return dronesdto;
	}

	@Override
	public List<BatteryCheckLogDTO> getBatteryCheckLog() {
		List<BatteryCheckLogDTO> log =  battService.getAllBatteryCheckLog().stream()
				.map(logTemp -> modelMapper.map(logTemp, BatteryCheckLogDTO.class))
				.collect(Collectors.toList());

        if (log.isEmpty()) {
            return new LinkedList<BatteryCheckLogDTO>();
        }
		return log;
	}

	@Override
	public Integer getCheckBatteryLevel(Long droneId) {
		Drone drone = droneService.getDroneById(droneId);
		return drone.getBatteryCapacity();
	}

	@Override
	public DroneDTO registerDrone(DroneDTO drone) {
		Drone droneRequest = modelMapper.map(drone, Drone.class); 
        droneRequest.setState(DroneState.Idle);
        Drone newDrone = droneService.createDrone(droneRequest);
        DroneDTO result = modelMapper.map(newDrone, DroneDTO.class);
		return result;
	}

	@Override
	public void registerOrder(OrderDTO order) throws WeightLimitException, NotAvailableDroneException, LowBatteryException, NoMedicineToLoadException {
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
		orderService.createOrder(new Order(drone, med));

	}

	@Override
	public OrderDTO showCargo(Long id) {
		Order order = orderService.findFirstByDroneIdAndDroneState(id, DroneState.Loaded);
		OrderDTO cargo = modelMapper.map(order, OrderDTO.class);
		return cargo;
	}

}
