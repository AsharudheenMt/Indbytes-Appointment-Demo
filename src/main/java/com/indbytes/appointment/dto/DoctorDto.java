package com.indbytes.appointment.dto;

import javax.validation.constraints.NotBlank;

public class DoctorDto {

	private int id;
	
	@NotBlank(message = "Doctor name is required")
	private String name;
	
	@NotBlank(message = "Speciality is required")
	private String speciality;

	// constructors
	public DoctorDto() {

	}

	public DoctorDto(int id, String name, String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.speciality = speciality;
	}

	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

}
