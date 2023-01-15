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
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
	
	public DroneDTO() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batteryCapacity;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weightLimit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DroneDTO other = (DroneDTO) obj;
		if (batteryCapacity != other.batteryCapacity)
			return false;
		if (id != other.id)
			return false;
		if (model != other.model)
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (state != other.state)
			return false;
		if (Double.doubleToLongBits(weightLimit) != Double.doubleToLongBits(other.weightLimit))
			return false;
		return true;
	}
	
	
	
}
