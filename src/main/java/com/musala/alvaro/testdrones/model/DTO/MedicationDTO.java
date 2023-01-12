package com.musala.alvaro.testdrones.model.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MedicationDTO {

	private long id;
	
	@Pattern(regexp = "^[a-zA-Z0-9-_]*$", message = "The medicine name only allows letters, numbers and special characters '-' and '_'")
    @NotBlank(message = "Medicine name is required")
	private String name;
	
	@NotNull(message = "Weight is required")
	private double weight;
	
	@Pattern(regexp = "^[A-Z0-9_]*$", message = "The medicine code only allows uppercase letters, numbers and special character '_'")
    @NotBlank(message = "Code is required")
	private String code;
	private String image;
	
	public MedicationDTO(long id, String name, double weight, String code, String image) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
	}
	
	public MedicationDTO() {
		super();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "MedicationDTO [id=" + id + ", name=" + name + ", weight=" + weight + ", code=" + code + ", image="
				+ image + "]";
	}
	
	
	
}
