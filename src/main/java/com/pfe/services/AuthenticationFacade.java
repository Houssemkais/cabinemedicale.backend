package com.pfe.services;

import com.pfe.entities.User;
import com.pfe.exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    @Autowired
    UserService userService;

    public User getConnectedUser() throws DomainException {
       return userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
