package com.bindschaedel.Controller;

import com.bindschaedel.Entity.Club;
import com.bindschaedel.Entity.ClubGroup;
import com.bindschaedel.Service.ClubService;
import com.bindschaedel.Service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClubController {

    private final ClubService clubService;
    private final GroupService groupService;

    public ClubController(ClubService clubService, GroupService groupService) {
        this.clubService = clubService;
        this.groupService = groupService;
    }

    @GetMapping("/clubs")
    public ResponseEntity<Iterable<Club>> getAllClubs() {
        return new ResponseEntity<>(clubService.getAll(), HttpStatus.OK);
    }

    //TODO Just won't work
    @GetMapping("/clubs/{clubId}")
    public ResponseEntity<Club> getSingleClub(@PathVariable(value = "clubId") String clubId) {
        
        return new ResponseEntity<>(clubService.findById(Long.parseLong(clubId)), HttpStatus.OK);
    }

    @GetMapping("/clubs/{clubId}/groups")
    public ResponseEntity<Iterable<ClubGroup>> getGroupsForClub(@PathVariable(value = "clubId") String clubId) {
        Long id = Long.parseLong(clubId);
        return new ResponseEntity<>(groupService.findGroupByClubId(id), HttpStatus.OK);
    }
}
