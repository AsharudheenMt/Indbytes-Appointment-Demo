package com.indbytes.appointment.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.indbytes.appointment.model.Appointment;
import com.indbytes.appointment.model.Doctor;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {
	@Query("FROM Appointment a WHERE a.patient.id = :patientId AND a.appointmentDate = current_date()")
	Optional<Appointment> checkForTodaysAppointment(int patientId);

	@Query("SELECT a.doctor FROM Appointment a WHERE a.patient.id = :patientId")
	List<Doctor> alreadyAppointedDoctorsForPatient(int patientId);

}
