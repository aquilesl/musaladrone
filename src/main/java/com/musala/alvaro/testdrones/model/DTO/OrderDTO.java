package com.musala.alvaro.testdrones.model.DTO;

import java.util.Set;
import javax.validation.constraints.NotNull;

public class OrderDTO {
	
	private long id;
	
	@NotNull(message = "Drone is required")
	private DroneDTO drone;
	
	@NotNull(message = "Medicine is required")
	private Set<MedicationDTO> medications;
	
	public OrderDTO(DroneDTO drone, Set<MedicationDTO> medications) {
		this.drone = drone;
		this.medications = medications;
	}
	
	public OrderDTO() {
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

	public Set<MedicationDTO> getMedications() {
		return medications;
	}

	public void setMedications(Set<MedicationDTO> medications) {
		this.medications = medications;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", drone=" + drone + ", medications=" + medications + "]";
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
		OrderDTO other = (OrderDTO) obj;
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
