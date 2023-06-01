package com.pfe.Controller;

import com.pfe.entities.Appointment;
import com.pfe.exception.DomainException;
import com.pfe.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(value = {"SECRATARY", "PATIENT"})
    public Appointment create(@Valid @RequestBody AppointmentCreateModel a) throws DomainException {
        return appointmentService.create(a);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SECRATARY')")
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(value = {"SECRATARY", "PATIENT", "DOCTOR"})
    public Appointment findById(@PathVariable(value = "id") Integer id) throws DomainException {
        return appointmentService.findById(id);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(value = {"SECRATARY", "PATIENT"})
    public Appointment update(@PathVariable(value = "id") Integer id, @Valid @RequestBody AppointmentUpdateModel appointmentUpdateModel) throws DomainException {
        return appointmentService.update(id, appointmentUpdateModel);
    }

    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Integer id) {
        appointmentService.delete(id);
    }

}



