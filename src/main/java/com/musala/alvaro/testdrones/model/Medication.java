package com.musala.alvaro.testdrones.model;

import javax.persistence.*;

@Entity
@Table(name = "medication")
public class Medication {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private double weight;

    @Column(name = "code")
    private String code;

    @Column(name = "image")
    private String image;

	public Medication(String name, double weight, String code, String image) {
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
	}

	public Medication() {
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
		return "Medication [id=" + id + ", name=" + name + ", weight=" + weight + ", code=" + code + ", image=" + image
				+ "]";
	}
    
    
	
}
