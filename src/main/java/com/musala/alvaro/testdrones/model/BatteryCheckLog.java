package com.musala.alvaro.testdrones.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "batterychecklog")
public class BatteryCheckLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Drone drone;

    @Column(name = "checkDateTime")
    private Date checkDateTime;

    @Column(name = "batteryLevel")
    private int batteryLevel;

	public BatteryCheckLog(Drone drone, Date checkTime, int batteryLevel) {
		super();
		this.drone = drone;
		this.checkDateTime = checkTime;
		this.batteryLevel = batteryLevel;
	}

	public BatteryCheckLog() {
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

	public Date getCheckTime() {
		return checkDateTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkDateTime = checkTime;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	@Override
	public String toString() {
		return "BatteryCheckLog [id=" + id + ", drone=" + drone + ", checkDateTime=" + checkDateTime + ", batteryLevel="
				+ batteryLevel + "]";
	}


}
