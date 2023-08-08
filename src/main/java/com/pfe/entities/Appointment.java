package com.pfe.entities;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
@Data
@Entity
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String reason;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    @NotNull(message = "status can't be null")
    private Status status;

}
