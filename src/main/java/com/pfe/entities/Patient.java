package com.pfe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@ToString
public class Patient extends User{

    public Patient() {
        this.setUserRole(UserRole.PATIENT);
    }
    private Sexe sex;
    private float weight;
    private float size;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="patient")
    private Set<Appointment> appointments;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="patient")
    private Set<Analysis> analyses;

    private String commentaries;


}
