package com.musala.alvaro.testdrones.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "batterychecklog")
public class BatteryCheckLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Drone drone;

    @Column(name = "checkDateTime")
    private Date checkDateTime;

    @Column(name = "batteryLevel")
    private int batteryLevel;

	public BatteryCheckLog(Drone drone, Date checkTime, int batteryLevel) {
		this.drone = drone;
		this.checkDateTime = checkTime;
		this.batteryLevel = batteryLevel;
	}

	public BatteryCheckLog() {

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
		BatteryCheckLog other = (BatteryCheckLog) obj;
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
