package com.musala.alvaro.testdrones.model.DTO;

import com.musala.alvaro.testdrones.model.GlobalConstant;
import com.musala.alvaro.testdrones.model.enums.DroneModel;
import com.musala.alvaro.testdrones.model.enums.DroneState;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DroneDTO {

	
	private long id;
	
	@NotBlank(message = "Serial number is required")
    @Size(max = GlobalConstant.MAX_SERIAL_NUMBER_LENGHT, message
            = "The serial number must have 100 characters maximum")
	private String serialNumber;
	
	@NotNull(message = "Drone Model is required")
	private DroneModel model;
	
	@NotNull(message = "Weight limit is required")
    @Max(value = GlobalConstant.WEIGHT_LIMIT, message
            = "The weight limit it's 500 gr")
	private double weightLimit;
	
	@NotNull(message = "Battery capacity is required")
    @Max(value = GlobalConstant.MAX_PERCENT, message
            = "The upper limit of the battery capacity is 100%")
	@Min(value = GlobalConstant.MIN_PERCENT, message
    = "The lower limit of the battery capacity is 1%")
	private int batteryCapacity;
	
	private DroneState state;
	
	public DroneDTO(String serialNumber, DroneModel model, double weightLimit, int batteryCapacity,
			DroneState state) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
	
	public DroneDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public DroneModel getModel() {
		return model;
	}
	public void setModel(DroneModel model) {
		this.model = model;
	}
	public double getWeightLimit() {
		return weightLimit;
	}
	public void setWeightLimit(double weightLimit) {
		this.weightLimit = weightLimit;
	}
	public double getBatteryCapacity() {
		return batteryCapacity;
	}
	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
	public DroneState getState() {
		return state;
	}
	public void setState(DroneState state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "DroneDTO [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weightLimit="
				+ weightLimit + ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}
	
	
	
}
