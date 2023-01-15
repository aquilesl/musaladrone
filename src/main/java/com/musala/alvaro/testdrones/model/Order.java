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

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Medication> medications;

	public Order(Drone drone, Set<Medication> medications) {
		this.drone = drone;
		this.medications = medications;
	}

	public Order() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drone == null) ? 0 : drone.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((medications == null) ? 0 : medications.hashCode());
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
		Order other = (Order) obj;
		if (drone == null) {
			if (other.drone != null)
				return false;
		} else if (!drone.equals(other.drone))
			return false;
		if (id != other.id)
			return false;
		if (medications == null) {
			if (other.medications != null)
				return false;
		} else if (!medications.equals(other.medications))
			return false;
		return true;
	}

	
}
