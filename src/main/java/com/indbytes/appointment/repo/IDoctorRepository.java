package com.indbytes.appointment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indbytes.appointment.model.Doctor;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Integer> {

}
