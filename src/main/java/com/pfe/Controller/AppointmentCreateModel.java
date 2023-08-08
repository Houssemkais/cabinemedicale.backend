package com.pfe.Controller;

import com.pfe.entities.Status;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@ToString
public class AppointmentCreateModel {

    private boolean isAvailable;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    @NotEmpty(message = "Reason can't be null")
    private String reason;
    @NotNull(message = "")
    private Integer patient_id;
    @NotNull(message = "")
    private Integer doctor_id;
    @NotNull(message = "")
    private Status status ;
}


