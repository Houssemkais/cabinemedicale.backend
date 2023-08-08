package com.pfe.services;

import com.pfe.Repository.UserRepository;
import com.pfe.entities.*;
import com.pfe.exception.DomainException;
import com.pfe.exception.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User create(User user) throws DomainException {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new DomainException(Error.USER_EMAIL_ALREADY_EXISTS);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
//    public void updatePassword(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }

    public User update(Integer id, User userUpdated) throws DomainException {
        User user = this.findById(id);
        userUpdated.setId(id);
        return userRepository.save(userUpdated);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) throws DomainException {
        return userRepository.findById(id).orElseThrow(() -> new DomainException(Error.NOT_FOUND_EXCEPTION));
    }

    public Patient findPatientById(Integer id) throws DomainException {
        return (Patient) userRepository.findPatientByIdAndUserRole(id, UserRole.PATIENT).orElseThrow(() -> new DomainException(Error.NOT_FOUND_EXCEPTION));
    }

    public Doctor findDoctorById(Integer id) throws DomainException {
        return (Doctor) userRepository.findPatientByIdAndUserRole(id, UserRole.DOCTOR).orElseThrow(() -> new DomainException(Error.NOT_FOUND_EXCEPTION));
    }

    public Secretary findSecretaryById(Integer id) throws DomainException {
        return (Secretary) userRepository.findPatientByIdAndUserRole(id, UserRole.SECRETARY).orElseThrow(() -> new DomainException(Error.NOT_FOUND_EXCEPTION));
    }

    public User findUserByEmail(String email) throws DomainException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new DomainException(Error.NOT_FOUND_EXCEPTION));
    }



}
