package com.bindschaedel.Controller;

import com.bindschaedel.Entity.Club;
import com.bindschaedel.Service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    private final ClubService clubService;

    public IndexController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Club>> index(){
        return new ResponseEntity<>(clubService.getAll(), HttpStatus.OK);
    }
}
