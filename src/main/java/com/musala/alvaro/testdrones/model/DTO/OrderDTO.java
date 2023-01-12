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
		super();
		this.drone = drone;
		this.medications = medications;
	}
	
	public OrderDTO() {
		super();
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
	
	

}
