package com.pfe.Controller;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
public class AppointmentCreateModel {
    @NotNull(message = "date can't be null")
    private Date date;
    @NotEmpty(message = "Reason can't be null")
    private String reason;
    @NotNull(message = "")
    private Integer createdBy_id;
    @NotNull(message = "")
    private Integer patient_id;
    @NotNull(message = "")
    private Integer doctor_id;
}

