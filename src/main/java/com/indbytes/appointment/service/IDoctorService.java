package com.indbytes.appointment.service;

import com.indbytes.appointment.dto.ApiResponse;
import com.indbytes.appointment.dto.DoctorDto;

public interface IDoctorService {

	ApiResponse createDoctor(DoctorDto doctor);

	ApiResponse getDoctorById(int id);

	ApiResponse getAllDoctors();

	ApiResponse updateDoctor(int id, DoctorDto doctorDto);

	ApiResponse deleteDoctor(int id);
}
