package com.pfe.configuration;

import com.pfe.exception.DomainException;
import com.pfe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userService.findUserByEmail(username);
        } catch (DomainException e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}