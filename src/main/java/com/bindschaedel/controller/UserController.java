package com.bindschaedel.controller;

import com.bindschaedel.entity.User;
import com.bindschaedel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.signUp(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}
