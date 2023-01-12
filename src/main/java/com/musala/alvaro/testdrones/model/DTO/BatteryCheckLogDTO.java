package com.musala.alvaro.testdrones.model.DTO;

import java.util.Date;

import com.musala.alvaro.testdrones.model.Drone;

public class BatteryCheckLogDTO {

	private long id;
	private Drone drone;
	private Date checkDateTime;
	private int batteryLevel;
	
	public BatteryCheckLogDTO(Drone drone, Date checkDateTime, int batteryLevel) {
		super();
		this.drone = drone;
		this.checkDateTime = checkDateTime;
		this.batteryLevel = batteryLevel;
	}
	
	public BatteryCheckLogDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Drone getDrone() {
		return drone;
	}
	public void setDrone(Drone drone) {
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

	
}
