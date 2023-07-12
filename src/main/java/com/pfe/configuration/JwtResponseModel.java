package com.pfe.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtResponseModel implements Serializable {
    private String token;

}
