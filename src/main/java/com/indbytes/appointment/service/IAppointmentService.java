package com.indbytes.appointment.service;

import com.indbytes.appointment.dto.ApiResponse;

public interface IAppointmentService {
	
	ApiResponse getAppointment(int patientId);
	

}
