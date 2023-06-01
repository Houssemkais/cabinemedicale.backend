package com.pfe.Controller;

import com.pfe.entities.Doctor;
import com.pfe.entities.User;
import com.pfe.exception.DomainException;
import com.pfe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid  @RequestBody User u) throws DomainException {
        return userService.create(u);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll() {
        return userService.findAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable(value = "id") Integer id) throws DomainException {
        return userService.findById(id);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable(value = "id") Integer id, @Valid @RequestBody User user) throws DomainException {
        return userService.update(id, user);
    }

    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Integer id) {
        userService.delete(id);
    }

}
