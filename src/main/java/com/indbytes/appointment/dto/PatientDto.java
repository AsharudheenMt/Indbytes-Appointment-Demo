package com.indbytes.appointment.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatientDto {

	private int id;

	@NotBlank(message = "patient name is required")
	private String name;

	@NotNull(message = "age")
	@Min(value = 0)
	private int age;

	@NotBlank(message = "contact number is required")
	private String contactNumber;

	public PatientDto() {
		super();
	}

	public PatientDto(int patientId, String name, int age, String contactNumber) {
		super();
		this.id = patientId;
		this.name = name;
		this.age = age;
		this.contactNumber = contactNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
