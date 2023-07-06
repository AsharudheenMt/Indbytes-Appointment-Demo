package com.indbytes.appointment.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.indbytes.appointment.dto.ApiResponse;
import com.indbytes.appointment.dto.AppointmentDto;
import com.indbytes.appointment.model.Appointment;
import com.indbytes.appointment.model.Doctor;
import com.indbytes.appointment.model.Patient;
import com.indbytes.appointment.repo.IAppointmentRepository;
import com.indbytes.appointment.service.IAppointmentService;
import com.indbytes.appointment.service.IDoctorService;

@Service
public class ApointmentServiceImpl implements IAppointmentService {
	@Autowired
	private IDoctorService doctorService;

	@Autowired
	private IAppointmentRepository appointmentRepository;

	@Override
	public ApiResponse getAppointment(int patientId) {

		// Check if the patient already has an appointment for today
		Optional<Appointment> appointmentOptional = appointmentRepository.checkForTodaysAppointment(patientId);

		if (appointmentOptional.isPresent()) {
			AppointmentDto dto = new AppointmentDto();
			Appointment appointment = appointmentOptional.get();
			dto.setAppointmentDate(appointment.getAppointmentDate());
			dto.setDoctorName(appointment.getDoctor().getName());
			dto.setSpeciality(appointment.getDoctor().getSpeciality());
			return new ApiResponse(false, HttpStatus.OK.value(), "Already Has Appointment For today", dto);
		}

		// Get the available doctors excluding the ones already appointed for the
		// patient
		List<Doctor> appointedDoctors = appointmentRepository.alreadyAppointedDoctorsForPatient(patientId);
		List<Doctor> availableDoctors = getAvailableDoctors(appointedDoctors);

		// Check if there are any available doctors
		if (availableDoctors.isEmpty()) {
			return new ApiResponse(false, HttpStatus.OK.value(), "All Doctors Appointed,No new Doctors", null);
		}

		// Get a random doctor from the available doctors list
		Random random = new Random();
		Doctor randomDoctor = availableDoctors.get(random.nextInt(availableDoctors.size()));

		// Create the appointment for the patient and the selected doctor
		Appointment appointment = createAppointment(patientId, randomDoctor);

		// show response
		AppointmentDto dto = new AppointmentDto();
		dto.setAppointmentDate(appointment.getAppointmentDate());
		dto.setDoctorName(appointment.getDoctor().getName());
		return new ApiResponse(true, HttpStatus.CREATED.value(), "Appointment Created", dto);
	}

	// Store the patient's appointment for today
	private Appointment createAppointment(int patientId, Doctor randomDoctor) {
		Appointment appointment = new Appointment();
		Patient patient = new Patient();
		patient.setId(patientId);
		appointment.setPatient(patient);
		appointment.setDoctor(randomDoctor);
		appointment.setAppointmentDate(LocalDate.now());
		return appointmentRepository.save(appointment);
	}

	private List<Doctor> getAvailableDoctors(List<Doctor> appointedDoctors) {
		List<Doctor> allDoctors = (List<Doctor>) doctorService.getAllDoctors().getData();
		List<Doctor> availableDoctors = new ArrayList<>();

		for (Doctor doctor : allDoctors) {
			if (!appointedDoctors.contains(doctor)) {
				availableDoctors.add(doctor);
			}
		}

		return availableDoctors;
	}

}
