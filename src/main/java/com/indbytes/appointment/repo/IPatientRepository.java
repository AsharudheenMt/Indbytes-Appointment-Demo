package com.indbytes.appointment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indbytes.appointment.model.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {

}
