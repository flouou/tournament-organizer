package com.bindschaedel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TournamentApplication extends SpringBootServletInitializer {
    public static void main(String[] args){
        SpringApplication.run(TournamentApplication.class, args);
    }
}
