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
import com.indbytes.appointment.dto.DoctorDto;
import com.indbytes.appointment.model.Doctor;
import com.indbytes.appointment.repo.IDoctorRepository;
import com.indbytes.appointment.service.IDoctorService;
import com.indbytes.appointment.util.ConstantMessages;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private IDoctorRepository doctorRepository;

	@Override
	public ApiResponse createDoctor(DoctorDto doctorDto) {
		try {
			Doctor newDoctor = new Doctor();
			BeanUtils.copyProperties(doctorDto, newDoctor);
			newDoctor = doctorRepository.save(newDoctor);
			return new ApiResponse(true, HttpStatus.CREATED.value(), ConstantMessages.CREATE_SUCCESS, newDoctor);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ConstantMessages.SOMETHING_WENT_WRONG, null);
		}
	}

	@Override
	public ApiResponse getDoctorById(int id) {
		try {
			Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
			if (optionalDoctor.isPresent()) {

				return new ApiResponse(true, HttpStatus.OK.value(), ConstantMessages.DATA_LOAD_SUCCESS,
						optionalDoctor.get());

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
	public ApiResponse getAllDoctors() {
		try {
			List<Doctor> doctorList = doctorRepository.findAll();
			return new ApiResponse(true, HttpStatus.OK.value(), ConstantMessages.DATA_LOAD_SUCCESS, doctorList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ConstantMessages.SOMETHING_WENT_WRONG, null);
		}
	}

	@Override
	public ApiResponse updateDoctor(int id, DoctorDto doctorDto) {
		try {
			ApiResponse response = getDoctorById(id);
			if (response.isSuccess()) {
				Doctor doctor = (Doctor) response.getData();
				BeanUtils.copyProperties(doctorDto, doctor);
				doctorRepository.save(doctor);
				return new ApiResponse(true, HttpStatus.OK.value(), ConstantMessages.UPDATE_SUCCESS, null);
			} else {
				return response;
			}
		} catch (BeansException e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ConstantMessages.SOMETHING_WENT_WRONG, null);
		}
	}

	@Override
	public ApiResponse deleteDoctor(int id) {
		try {
			doctorRepository.deleteById(id);
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
