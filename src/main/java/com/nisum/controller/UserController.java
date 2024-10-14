package com.nisum.controller;

import com.nisum.entity.User;
import com.nisum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired UserService userService;

    /**
     * Crea usuario
     * @param user
     * @return ResponseEntity<Object>
     */

    @RequestMapping (value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  register(@RequestBody User user) throws Exception {
        return userService.register(user);
    }


}
