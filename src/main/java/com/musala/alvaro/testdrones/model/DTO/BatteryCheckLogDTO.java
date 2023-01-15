package com.musala.alvaro.testdrones.model.DTO;

import java.util.Date;


public class BatteryCheckLogDTO {

	private long id;
	private DroneDTO drone;
	private Date checkDateTime;
	private int batteryLevel;
	
	public BatteryCheckLogDTO(DroneDTO drone, Date checkDateTime, int batteryLevel) {
		this.drone = drone;
		this.checkDateTime = checkDateTime;
		this.batteryLevel = batteryLevel;
	}
	
	public BatteryCheckLogDTO() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public DroneDTO getDrone() {
		return drone;
	}
	public void setDrone(DroneDTO drone) {
		this.drone = drone;
	}
	public Date getCheckDateTime() {
		return checkDateTime;
	}
	public void setCheckDateTime(Date checkDateTime) {
		this.checkDateTime = checkDateTime;
	}
	public int getBatteryLevel() {
		return batteryLevel;
	}
	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	@Override
	public String toString() {
		return "BatteryCheckLogDTO [id=" + id + ", drone=" + drone + ", checkDateTime=" + checkDateTime
				+ ", batteryLevel=" + batteryLevel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batteryLevel;
		result = prime * result + ((checkDateTime == null) ? 0 : checkDateTime.hashCode());
		result = prime * result + ((drone == null) ? 0 : drone.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		BatteryCheckLogDTO other = (BatteryCheckLogDTO) obj;
		if (batteryLevel != other.batteryLevel)
			return false;
		if (checkDateTime == null) {
			if (other.checkDateTime != null)
				return false;
		} else if (!checkDateTime.equals(other.checkDateTime))
			return false;
		if (drone == null) {
			if (other.drone != null)
				return false;
		} else if (!drone.equals(other.drone))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	

	
}
