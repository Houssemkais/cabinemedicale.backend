package com.pfe.services;

import com.pfe.Controller.AppointmentCreateModel;
import com.pfe.Controller.AppointmentUpdateModel;
import com.pfe.Repository.AppointmentRepository;
import com.pfe.entities.Appointment;
import com.pfe.entities.Doctor;
import com.pfe.entities.Patient;
import com.pfe.exception.DomainException;
import com.pfe.exception.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    public Appointment create(AppointmentCreateModel appointmentCreateModel) throws DomainException {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentCreateModel.getDate());
        appointment.setReason(appointmentCreateModel.getReason());
        log.info(appointmentCreateModel.toString());
        appointment.setPatient(userService.findPatientById(appointmentCreateModel.getPatient_id()));
        appointment.setDoctor(userService.findDoctorById(appointmentCreateModel.getDoctor_id()));
        appointment.setCreatedBy(userService.findById(appointmentCreateModel.getCreatedBy_id()));
        return appointmentRepository.save(appointment);
    }

    public Appointment update(Integer id, AppointmentUpdateModel appointmentUpdateModel) throws DomainException {
        Appointment appointment = this.findById(id);
        appointment.setDate(appointmentUpdateModel.getDate());
        appointment.setReason(appointmentUpdateModel.getReason());
        return appointmentRepository.save(appointment);
    }

    public void delete(Integer id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Integer id) throws DomainException {
        return appointmentRepository.findById(id).orElseThrow(() -> new DomainException(Error.NOT_FOUND_EXCEPTION));
    }
}
