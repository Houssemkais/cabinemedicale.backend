package com.pfe.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date analysisDate;
    private String analysisType;
    private  String results;
    private  String comments;
    @ManyToOne
    private Patient patient;

}
