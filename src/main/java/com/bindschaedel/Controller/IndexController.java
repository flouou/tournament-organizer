package com.bindschaedel.Controller;

import org.springframework.stereotype.Controller;

@Controller
public class IndexController {
    
    public String index() {
        return "Hello";
    }
}
