package com.backend.guestnhouse.controllers;

import com.backend.guestnhouse.models.Role;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.repository.RoleRepository;
import com.backend.guestnhouse.repository.UserRepository;
import com.backend.guestnhouse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
    public String updateHouse(@PathVariable("id") String id, @Valid @RequestBody User user) {
        return userService.updateUser(id, user);
    }
   /* @RequestMapping(value = "/archiveUser/{id}", method = RequestMethod.DELETE)
    public String archiveUser(@PathVariable("id") String id) {
        return userService.acrchiveUser(id);
    }

    @RequestMapping(value = "/dearchvie/{id}", method = RequestMethod.DELETE)
    public String dearchiveUser(@PathVariable("id") String id) {
        return userService.deacrchiveUser(id);
    }*/
    //Ban user
    @RequestMapping(value = "/archiveUser/{id}", method = RequestMethod.PUT)
    public void banUserById(@PathVariable("id") String id) {
        User user = userRepository.findById(id).get();
        user.setId(id);
        user.setArchived(1);
        userRepository.save(user);
    }
    //UnBan user

    @RequestMapping(value = "/dearchvieUser/{id}", method = RequestMethod.PUT)
    public void unbanUserById(@PathVariable("id") String id) {
        User user = userRepository.findById(id).get();
        user.setId(id);
        user.setArchived(0);
        userRepository.save(user);
    }
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();

    }
    //afficher un user selon id

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") String id) {
        return userRepository.findById(id).orElse(null);
    }
    //Affecter role a un user

    @RequestMapping(value = "/user/{id}/{role}", method = RequestMethod.PUT)
    public void assignRoleToUserById(@PathVariable("id") String id, @PathVariable("role") String role) {
        User user = userRepository.findById(id).orElse(null);
        user.setId(id);
        user.getRoles().clear();
        user.getRoles().add(roleRepository.findRoleByName(role));
        userRepository.save(user);
    }
    @RequestMapping(value = "/password/{id}", method = RequestMethod.PUT)
    public ResponseEntity editpassword(@PathVariable("id") String id, @Valid @RequestBody Password body) {
        User user = userRepository.findById(id).get();
        if (!passwordEncoder.matches(body.getAncienPassword(),user.getPassword()))
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            user.setPassword(passwordEncoder.encode(body.getNewPassword()));
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }



}
