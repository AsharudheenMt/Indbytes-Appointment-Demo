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
import com.indbytes.appointment.dto.PatientDto;
import com.indbytes.appointment.service.IPatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private IPatientService patientService;

	@PostMapping
	public ApiResponse createPatient(@RequestBody @Valid PatientDto patientDto) {
		return patientService.createPatient(patientDto);

	}

	@GetMapping("/{id}")
	public ApiResponse getPatientById(@PathVariable int id) {
		return patientService.getPatientById(id);
	}

	@GetMapping
	public ApiResponse getAllPatients() {
		return patientService.getAllPatients();
	}

	@PutMapping("/{id}")
	public ApiResponse updatePatient(@PathVariable int id, @RequestBody @Valid PatientDto patientDto) {
		return patientService.updatePatient(id, patientDto);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deletePatient(@PathVariable int id) {
		return patientService.deletePatient(id);

	}
}
