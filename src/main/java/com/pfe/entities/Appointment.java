package com.pfe.entities;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@Entity
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private String reason;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "status can't be null")
    private Status status;


}
