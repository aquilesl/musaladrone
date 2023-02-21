package com.musala.alvaro.testdrones.business;

import java.util.List;

import com.musala.alvaro.testdrones.exceptions.LowBatteryException;
import com.musala.alvaro.testdrones.exceptions.NoMedicineToLoadException;
import com.musala.alvaro.testdrones.exceptions.NotAvailableDroneException;
import com.musala.alvaro.testdrones.exceptions.WeightLimitException;
import com.musala.alvaro.testdrones.model.DTO.BatteryCheckLogDTO;
import com.musala.alvaro.testdrones.model.DTO.DroneDTO;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO; 

public interface IFleetBusiness {
	
	public List<DroneDTO> getAvailableDrones();
	public List<BatteryCheckLogDTO> getBatteryCheckLog();
	public Integer getCheckBatteryLevel(Long droneId);
	public DroneDTO registerDrone(DroneDTO drone);
	public void registerOrder(OrderDTO order) throws WeightLimitException, NotAvailableDroneException, LowBatteryException, NoMedicineToLoadException;
	public OrderDTO showCargo(Long droneId);

}
