package com.pfe.Repository;

import com.pfe.entities.Appointment;
import com.pfe.entities.Doctor;
import com.pfe.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDate(LocalDate date);

   /* @Query(value = "SELECT p FROM Appointment p WHERE p.doctor = :doctor AND p.status = :status AND p.date = :appointmentDate AND (:startTime BETWEEN p.startTime AND p.endTime) OR (:endTime BETWEEN p.startTime AND p.endTime)")
    List<Appointment> findAllAppointmentByDoctorAndDateAndStartTimeAndEndTimeAndStatus(Doctor doctor, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, Status status);*/
   @Query(value = "SELECT p FROM Appointment p WHERE p.doctor = :doctor AND p.status = :status AND p.date = :appointmentDate AND ((:startTime BETWEEN p.startTime AND p.endTime) OR (:endTime BETWEEN p.startTime AND p.endTime))")
   List<Appointment> findAllAppointmentByDoctorAndDateAndStartTimeAndEndTimeAndStatus(Doctor doctor, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, Status status);

}
