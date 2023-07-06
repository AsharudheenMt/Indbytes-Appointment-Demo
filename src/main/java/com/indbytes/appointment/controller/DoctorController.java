package com.indbytes.appointment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indbytes.appointment.dto.ApiResponse;
import com.indbytes.appointment.dto.DoctorDto;
import com.indbytes.appointment.service.IDoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;

	@PostMapping
	public ApiResponse createDoctor(@RequestBody @Valid DoctorDto doctorDto) {
		return doctorService.createDoctor(doctorDto);

	}

	@GetMapping("/{id}")
	public ApiResponse getDoctorById(@PathVariable int id) {
		return doctorService.getDoctorById(id);
	}

	@GetMapping
	public ApiResponse getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	@PutMapping("/{id}")
	public ApiResponse updateDoctor(@PathVariable int id, @RequestBody @Valid DoctorDto doctorDto) {
		return doctorService.updateDoctor(id, doctorDto);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteDoctor(@PathVariable int id) {
		return doctorService.deleteDoctor(id);
		
	}
}