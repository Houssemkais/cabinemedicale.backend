package com.pfe.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@ToString
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String diagnosis;
    @ElementCollection
    @MapKeyColumn(name = "medical_history_key")
    Map<String, Integer> medicalHistory = new HashMap<>();
    private Date date;
    @ManyToOne
    private User createdBy;
    @OneToOne
    private User CreatedFor;




}
