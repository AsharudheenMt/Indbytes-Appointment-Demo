package com.indbytes.appointment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.indbytes.appointment.dto.ApiResponse;
import com.indbytes.appointment.dto.PatientDto;
import com.indbytes.appointment.model.Patient;
import com.indbytes.appointment.repo.IPatientRepository;
import com.indbytes.appointment.service.IPatientService;
import com.indbytes.appointment.util.ConstantMessages;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private IPatientRepository patientRepository;

	@Override
	public ApiResponse createPatient(PatientDto patientDto) {
		try {
			Patient newPatient = new Patient();
			BeanUtils.copyProperties(patientDto, newPatient);
			newPatient = patientRepository.save(newPatient);
			return new ApiResponse(true, HttpStatus.CREATED.value(), ConstantMessages.CREATE_SUCCESS, newPatient);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ConstantMessages.SOMETHING_WENT_WRONG, null);
		}
	}

	@Override
	public ApiResponse getPatientById(int id) {
		try {
			Optional<Patient> optionalPatient = patientRepository.findById(id);
			if (optionalPatient.isPresent()) {

				return new ApiResponse(true, HttpStatus.OK.value(), ConstantMessages.DATA_LOAD_SUCCESS,
						optionalPatient.get());

			} else {

				return new ApiResponse(false, HttpStatus.NOT_FOUND.value(), ConstantMessages.ID_NOT_FOUND, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ConstantMessages.SOMETHING_WENT_WRONG, null);
		}
	}

	@Override
	public ApiResponse getAllPatients() {
		try {
			List<Patient> patientList = patientRepository.findAll();
			return new ApiResponse(true, HttpStatus.OK.value(), ConstantMessages.DATA_LOAD_SUCCESS, patientList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ConstantMessages.SOMETHING_WENT_WRONG, null);
		}
	}

	@Override
	public ApiResponse updatePatient(int id, PatientDto patientDto) {
		try {
			ApiResponse findByIdResponse = getPatientById(id);
			if (findByIdResponse.isSuccess()) {
				Patient patient = (Patient) findByIdResponse.getData();
				BeanUtils.copyProperties(patientDto, patient);
				patientRepository.save(patient);
				return new ApiResponse(true, HttpStatus.OK.value(), ConstantMessages.UPDATE_SUCCESS, null);
			} else {
				return findByIdResponse;
			}
		} catch (BeansException e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ConstantMessages.SOMETHING_WENT_WRONG, null);
		}
	}

	@Override
	public ApiResponse deletePatient(int id) {
		try {
			patientRepository.deleteById(id);
			return new ApiResponse(true, HttpStatus.NO_CONTENT.value(), ConstantMessages.DELETE_SUCCESS, null);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.NO_CONTENT.value(), ConstantMessages.ID_NOT_FOUND, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ConstantMessages.SOMETHING_WENT_WRONG, null);
		}
	}

}
