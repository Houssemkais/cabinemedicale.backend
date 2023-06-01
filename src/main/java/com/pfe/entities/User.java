package com.pfe.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pfe.utils.AbstractEntity;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "userRole")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Patient.class, name = "PATIENT"),
        @JsonSubTypes.Type(value = Doctor.class, name = "DOCTOR"),
        @JsonSubTypes.Type(value = Secretary.class, name = "SECRETARY"),
        @JsonSubTypes.Type(value = Admin.class, name = "ADMIN"),
})
@Schema(subTypes = {Patient.class, Doctor.class, Secretary.class},
        discriminatorMapping = {
                @DiscriminatorMapping(schema = Patient.class, value = "PATIENT"),
                @DiscriminatorMapping(schema = Doctor.class, value = "DOCTOR"),
                @DiscriminatorMapping(schema = Secretary.class, value = "SECRETARY"),
                @DiscriminatorMapping(schema = Admin.class, value = "ADMIN")},
        discriminatorProperty = "userRole"
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends AbstractEntity implements UserDetails {
    /*
     * Use AbstractEntity for all
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotEmpty(message = "lastname can't be null")
    private String lastname;
    @NotEmpty(message = "firstname can't be null")
    private String firstname;
    @NotEmpty(message = "email can't be null")
    //@Pattern(regexp="^(.+)@(\\S+) $", message="Please provide a valid email address")
    private String email;
    @NotEmpty(message = "password can't be null")
    private String password;
    @NotNull(message = "date of birth can't be null")
    private Date dateOfBirth;
    @NotEmpty(message = "phone can't be null")
    private String phone;
    @NotEmpty(message = "address can't be null")
    private String address;
    @NotNull(message = "role can't be null")
    private UserRole userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
