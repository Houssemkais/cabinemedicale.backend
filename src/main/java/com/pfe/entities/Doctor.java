package com.pfe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@ToString
public class Doctor extends User {

    public Doctor() {
        this.setUserRole(UserRole.DOCTOR);
    }

    private String specialty;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="doctor")
    private Set<Appointment> appointments;

}
