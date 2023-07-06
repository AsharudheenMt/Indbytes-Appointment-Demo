package com.indbytes.appointment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "speciality")
	private String speciality;

	public Doctor() {
	}

	public Doctor(int doctorId, String name, String speciality) {
		super();
		this.id = doctorId;
		this.name = name;
		this.speciality = speciality;
	}

	public int getDoctorId() {
		return id;
	}

	public void setDoctorId(int doctorId) {
		this.id = doctorId;
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
