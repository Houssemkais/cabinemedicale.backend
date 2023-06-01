package com.pfe.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@ToString
public class Secretary extends User{

    public Secretary() {
        this.setUserRole(UserRole.SECRETAIRE);
    }

}
