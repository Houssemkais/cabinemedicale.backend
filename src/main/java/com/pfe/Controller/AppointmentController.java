package com.pfe.Controller;

import com.pfe.entities.Appointment;
import com.pfe.entities.User;
import com.pfe.exception.DomainException;
import com.pfe.services.AppointmentService;
import com.pfe.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(value = {"ADMIN", "SECRATARY", "PATIENT"})
    public Appointment create(@Valid @RequestBody AppointmentCreateModel a) throws DomainException {
        return appointmentService.create(a);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "findAll", produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public Appointment findById(@PathVariable(value = "id") Integer id) throws DomainException {
        return appointmentService.findById(id);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)

    public Appointment update(@PathVariable(value = "id") Integer id, @Valid @RequestBody AppointmentUpdateModel appointmentUpdateModel ) throws DomainException {
        return appointmentService.update(id, appointmentUpdateModel );
    }

    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Integer id) {
        appointmentService.delete(id);
    }
    @PutMapping(path = "/appointment/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void cancelAppointment(@PathVariable(value = "id") Integer id, @Valid @RequestBody AppointmentUpdateModel appointmentUpdateModel) throws DomainException{
        appointmentService.cancelAppointment(id,appointmentUpdateModel);
    }

}



