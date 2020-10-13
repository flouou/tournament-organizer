package com.bindschaedel.controller;

import com.bindschaedel.entity.Club;
import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.service.ClubService;
import com.bindschaedel.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClubController {

    private final ClubService  clubService;
    private final GroupService groupService;

    public ClubController(ClubService clubService, GroupService groupService) {
        this.clubService = clubService;
        this.groupService = groupService;
    }

    @GetMapping("/clubs")
    public ResponseEntity<Iterable<Club>> getAllClubs() {
        return new ResponseEntity<>(clubService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/clubs/{clubId}")
    public ResponseEntity<Club> getSingleClub(@PathVariable(value = "clubId") String clubId) {

        return new ResponseEntity<>(clubService.findById(Long.parseLong(clubId)), HttpStatus.OK);
    }

    @GetMapping("/clubs/{clubId}/groups")
    public ResponseEntity<Iterable<ClubGroup>> getGroupsForClub(@PathVariable(value = "clubId") String clubId) {
        Long id = Long.parseLong(clubId);
        return new ResponseEntity<>(groupService.findGroupByClubId(id), HttpStatus.OK);
    }

    @PostMapping("/clubs")
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club savedClub = clubService.save(club);

        HttpStatus status;
        if (savedClub != null) {
            status = HttpStatus.CREATED;
        }
        else {
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(savedClub, status);
    }
    
    @DeleteMapping("/clubs/{clubId}")
    public ResponseEntity<Boolean> deleteClub(@PathVariable(value = "clubId") String clubId) {
        if (clubService.findById(Long.parseLong(clubId)) != null) {
            clubService.remove(Long.parseLong(clubId));
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
    }
}
