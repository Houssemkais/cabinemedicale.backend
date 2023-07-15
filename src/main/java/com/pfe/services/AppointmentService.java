package com.pfe.services;

import com.pfe.Controller.AppointmentCreateModel;
import com.pfe.Controller.AppointmentUpdateModel;
import com.pfe.Repository.AppointmentRepository;
import com.pfe.entities.Appointment;
import com.pfe.exception.DomainException;
import com.pfe.exception.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

 public Appointment create(AppointmentCreateModel appointmentCreateModel) throws DomainException {
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
 }
/*public Appointment create(AppointmentCreateModel appointmentCreateModel) throws DomainException {
    Appointment appointment = new Appointment();
    LocalDateTime startDateTime = appointmentCreateModel.getStartDateTime();
    LocalDateTime endDateTime = startDateTime.plusHours(1); // Assuming appointment duration is 1 hour
    LocalDate appointmentDate = startDateTime.toLocalDate();

    // Check if the chosen appointment date is available
    if (!isAppointmentDateAvailable(appointmentDate, null)) {
        throw new DomainException("Selected appointment date is not available.");
    }

    appointment.setStartDateTime(startDateTime);
    appointment.setEndDateTime(endDateTime);
    appointment.setReason(appointmentCreateModel.getReason());
    appointment.setPatient(userService.findPatientById(appointmentCreateModel.getPatient_id()));
    appointment.setDoctor(userService.findDoctorById(appointmentCreateModel.getDoctor_id()));
    appointment.setCreatedBy(authenticationFacade.getConnectedUser());
    appointment.setStatus(appointmentCreateModel.getStatus());
    appointment.setAvailable(false); // Marking the appointment as unavailable

    return appointmentRepository.save(appointment);
}

    // Helper method to check if the appointment date is available
    private boolean isAppointmentDateAvailable(LocalDate appointmentDate, Integer currentAppointmentId) {
        List<Appointment> appointments = appointmentRepository.findByDate(appointmentDate);
        for (Appointment appointment : appointments) {
            if (!appointment.isAvailable() && !appointment.getId().equals(currentAppointmentId)) {
                return false;
            }
        }
        return true;
    }*/


    public Appointment update(Integer id, AppointmentUpdateModel appointmentUpdateModel) throws DomainException {
        Appointment appointment = this.findById(id);
        appointment.setDate(appointmentUpdateModel.getDate());
        /*appointment.setEndDateTime(appointmentUpdateModel.getEndDateTime());*/
        appointment.setReason(appointmentUpdateModel.getReason());

        return appointmentRepository.save(appointment);
    }

    public void delete(Integer id) {
        appointmentRepository.deleteById(id);
    }

    public void cancelAppointment(Integer id,AppointmentUpdateModel appointmentUpdateModel) throws DomainException {
        Appointment appointment = this.findById(id);
        appointment.setStatus(appointment.getStatus().ANNULE);
        appointment.setAvailable(true);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Integer id) throws DomainException {
        return appointmentRepository.findById(id).orElseThrow(() -> new DomainException(Error.NOT_FOUND_EXCEPTION));
    }
}
