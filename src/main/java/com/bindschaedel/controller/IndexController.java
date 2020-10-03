package com.bindschaedel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
