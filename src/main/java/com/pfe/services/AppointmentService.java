package com.pfe.services;

import com.pfe.Controller.AppointmentCreateModel;
import com.pfe.Controller.AppointmentUpdateModel;
import com.pfe.Repository.AppointmentRepository;
import com.pfe.entities.Appointment;
import com.pfe.entities.Doctor;
import com.pfe.entities.Status;
import com.pfe.exception.DomainException;
import com.pfe.exception.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    /*public Appointment create(AppointmentCreateModel appointmentCreateModel) throws DomainException {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentCreateModel.getDate());
        //   appointment.setEndDateTime(appointmentCreateModel.getEndDateTime());
        appointment.setReason(appointmentCreateModel.getReason());
        log.info(appointmentCreateModel.toString());
        appointment.setPatient(userService.findPatientById(appointmentCreateModel.getPatient_id()));
        appointment.setDoctor(userService.findDoctorById(appointmentCreateModel.getDoctor_id()));
        appointment.setCreatedBy(authenticationFacade.getConnectedUser());
        appointment.setStatus(appointmentCreateModel.getStatus());
        appointment.setAvailable(true);
        return appointmentRepository.save(appointment);
    }*/
    public Appointment create(AppointmentCreateModel appointmentCreateModel) throws DomainException {
        Appointment appointment = new Appointment();
        LocalTime startTime = appointmentCreateModel.getStartTime();
        LocalTime endTime = startTime.plusHours(1); // Assuming appointment duration is 1 hour
        Doctor doctor = userService.findDoctorById(appointmentCreateModel.getDoctor_id());
        if (!appointmentRepository.findAllAppointmentByDoctorAndDateAndStartTimeAndEndTimeAndStatus(doctor, appointmentCreateModel.getDate(), startTime, endTime, Status.EN_COURS).isEmpty()) {
            throw new DomainException(Error.BAD_REQUEST);
        }
        appointment.setEndTime(endTime);
        appointment.setStartTime(startTime);
appointment.setDate(appointmentCreateModel.getDate());
        appointment.setReason(appointmentCreateModel.getReason());
        appointment.setPatient(userService.findPatientById(appointmentCreateModel.getPatient_id()));
        appointment.setDoctor(doctor);
        appointment.setCreatedBy(authenticationFacade.getConnectedUser());
        appointment.setStatus(appointmentCreateModel.getStatus());
        return appointmentRepository.save(appointment);
    }


    public Appointment update(Integer id, AppointmentUpdateModel appointmentUpdateModel ) throws DomainException {
        Appointment appointment = this.findById(id);
        LocalTime startTime = appointmentUpdateModel.getStartTime();
        LocalTime endTime = startTime.plusHours(1);
        Doctor doctor = userService.findDoctorById(appointmentUpdateModel.getDoctor_id());// Assuming appointment duration is 1 hour
        if (!appointmentRepository.findAllAppointmentByDoctorAndDateAndStartTimeAndEndTimeAndStatus(doctor, appointmentUpdateModel.getDate(), startTime, endTime, Status.EN_COURS).isEmpty()) {
            throw new DomainException(Error.BAD_REQUEST);
        }
        appointment.setEndTime(endTime);
        appointment.setStartTime(startTime);
        appointment.setReason(appointmentUpdateModel.getReason());
        appointment.setDate(appointmentUpdateModel.getDate());
        return appointmentRepository.save(appointment);
    }

    public void delete(Integer id) {
        appointmentRepository.deleteById(id);
    }

    public void cancelAppointment(Integer id, AppointmentUpdateModel appointmentUpdateModel) throws DomainException {
        Appointment appointment = this.findById(id);
        appointment.setStatus(appointment.getStatus().ANNULE);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Integer id) throws DomainException {
        return appointmentRepository.findById(id).orElseThrow(() -> new DomainException(Error.NOT_FOUND_EXCEPTION));
    }
}
