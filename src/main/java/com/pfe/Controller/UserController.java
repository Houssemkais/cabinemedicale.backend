package com.pfe.Controller;

import com.pfe.entities.User;
import com.pfe.entities.UserRole;
import com.pfe.exception.DomainException;
import com.pfe.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"ADMIN", "SECRETARY"})
    public User create(@Valid @RequestBody User u, Principal principal) throws Exception {
       User connectedUser = userService.findUserByEmail(principal.getName());
        if (UserRole.SECRETARY.equals(connectedUser.getUserRole())) {
           if (!Arrays.asList(UserRole.PATIENT).contains(u.getUserRole())) {
                throw new DomainException(com.pfe.exception.Error.UNOTHORIZED);
    }
       }
        return userService.create(u);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainException(DomainException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({"ADMIN", "SECRETARY", "DOCTOR"})
    public List<User> findAll() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({"ADMIN", "SECRETARY", "DOCTOR"})
    public User findById(@PathVariable(value = "id") Integer id) throws DomainException {
        return userService.findById(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({"ADMIN", "SECRETARY", "DOCTOR"})
    public User findByEmail(@PathVariable(value = "email") String email) throws DomainException {
        return userService.findUserByEmail(email);
    }
    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ADMIN", "SECRETARY", "DOCTOR"})
    public User update(@PathVariable(value = "id") Integer id, @Valid @RequestBody User user) throws DomainException {
        return userService.update(id, user);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Integer id) {
        userService.delete(id);
    }

}
