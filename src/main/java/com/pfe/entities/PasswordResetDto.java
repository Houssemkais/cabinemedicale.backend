package com.pfe.entities;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
@Data


public class PasswordResetDto {
    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;


}
