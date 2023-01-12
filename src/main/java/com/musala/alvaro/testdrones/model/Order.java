package com.musala.alvaro.testdrones.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Drone drone;

    @ManyToMany
    private Set<Medication> medications;

	public Order(Drone drone, Set<Medication> medications) {
		super();
		this.drone = drone;
		this.medications = medications;
	}

	public Order() {
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

	public Set<Medication> getMedications() {
		return medications;
	}

	public void setMedications(Set<Medication> medications) {
		this.medications = medications;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", drone=" + drone + ", medications=" + medications + "]";
	}

	
}
