package com.indbytes.appointment.dto;

import java.time.LocalDate;

public class AppointmentDto {

	private LocalDate appointmentDate;
	private String doctorName;
	private String speciality;

	public AppointmentDto() {
	}

	public AppointmentDto(LocalDate appointmentDate, String doctorName, String speciality) {
		super();
		this.appointmentDate = appointmentDate;
		this.doctorName = doctorName;
		this.speciality = speciality;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

}
