package com.indbytes.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indbytes.appointment.dto.ApiResponse;
import com.indbytes.appointment.service.IAppointmentService;

@RestController
@RequestMapping("/api/token")
public class AppointmentController {
	@Autowired
	private IAppointmentService appointmentService;

	@GetMapping("/{patientId}")
	public ApiResponse createAppointment(@PathVariable int patientId) {
		return appointmentService.getAppointment(patientId);
	}
}
