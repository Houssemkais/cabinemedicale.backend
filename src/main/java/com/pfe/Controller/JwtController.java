package com.pfe.Controller;

import com.pfe.configuration.JwtRequestModel;
import com.pfe.configuration.JwtResponseModel;
import com.pfe.configuration.TokenManager;
import com.pfe.configuration.UserDetailServiceImpl;
import com.pfe.entities.User;
import com.pfe.entities.UserRole;
import com.pfe.exception.DomainException;
import com.pfe.exception.Error;
import com.pfe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class JwtController {
    @Autowired
    private UserDetailServiceImpl userDetailsServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public JwtResponseModel createToken(@RequestBody JwtRequestModel
                                              request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new DomainException(Error.BAD_CREDENTIEL);
        }
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return new JwtResponseModel(jwtToken);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@Valid @RequestBody User u) throws DomainException {
        u.setUserRole(UserRole.PATIENT);
        return userService.create(u);
    }

}