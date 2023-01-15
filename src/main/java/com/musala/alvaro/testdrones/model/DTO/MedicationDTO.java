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
	
	public MedicationDTO(String name, double weight, String code, String image) {
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
	}
	
	public MedicationDTO() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		MedicationDTO other = (MedicationDTO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	
	
	
}
