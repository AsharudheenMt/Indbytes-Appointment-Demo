package com.indbytes.appointment.service;

import com.indbytes.appointment.dto.ApiResponse;
import com.indbytes.appointment.dto.PatientDto;

public interface IPatientService {
	
	ApiResponse createPatient(PatientDto patient);

	ApiResponse getPatientById(int id);

	ApiResponse getAllPatients();

	ApiResponse updatePatient(int id, PatientDto patientDto);

	ApiResponse deletePatient(int id);
}
