package com.bindschaedel.controller.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @GetMapping("/dummy")
    public String dummy() {
        return "";
    }
}
