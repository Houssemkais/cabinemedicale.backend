package com.pfe.Controller;

import com.pfe.entities.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AppointmentUpdateModel {
    private boolean isAvailable;
    private Date date;


    public boolean isAvailable() {
        return isAvailable;
    }
  /*  private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;*/
    @NotEmpty(message = "Reason can't be null")
    private String reason;
    @NotNull(message = "")
    private Status status;
}
