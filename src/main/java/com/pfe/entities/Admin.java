package com.pfe.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@Entity
@ToString
public class Admin extends User {

    public Admin() {
        this.setUserRole(UserRole.ADMIN);
    }


}
