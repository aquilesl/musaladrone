package com.musala.alvaro.testdrones.model;

import javax.persistence.*;

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

	public Medication(long id, String name, double weight, String code, String image) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
	}

	public Medication() {
		super();
	}

	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + ", weight=" + weight + ", code=" + code + ", image=" + image
				+ "]";
	}
    
    
	
}
