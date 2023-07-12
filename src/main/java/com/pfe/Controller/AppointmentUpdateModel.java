package com.pfe.Controller;

import com.pfe.entities.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AppointmentUpdateModel {
    @NotNull(message = "date can't be null")
    private Date date;
    @NotEmpty(message = "Reason can't be null")
    private String reason;
    @NotNull(message = "")
    private Status status;
}
