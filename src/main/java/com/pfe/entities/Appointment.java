package com.pfe.entities;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
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

}
