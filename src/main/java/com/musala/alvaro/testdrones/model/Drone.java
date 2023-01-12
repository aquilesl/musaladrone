package com.musala.alvaro.testdrones.model;

import javax.persistence.*;
import com.musala.alvaro.testdrones.model.enums.DroneModel;
import com.musala.alvaro.testdrones.model.enums.DroneState;

@Entity
@Table(name = "drone")
public class Drone {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "serialNumber")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private DroneModel model;

    @Column(name = "weightLimit")
    private double weightLimit;

    @Column(name = "batteryCapacity")
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state;

	public Drone(String serialNumber, DroneModel model, double weightLimit, int batteryCapacity,
			DroneState state) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
	
	public Drone() {
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

	public int getBatteryCapacity() {
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
		return "Drone [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weightLimit="
				+ weightLimit + ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}


	
}
