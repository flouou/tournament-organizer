package com.bindschaedel.controller;

import com.bindschaedel.entity.Club;
import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.service.ClubService;
import com.bindschaedel.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    private final GroupService groupService;
    private final ClubService  clubService;

    public GroupController(GroupService groupService, ClubService clubService) {
        this.groupService = groupService;
        this.clubService = clubService;
    }

    @GetMapping("/groups")
    public ResponseEntity<Iterable<ClubGroup>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<ClubGroup> getGroupById(@PathVariable(value = "groupId") String groupId) {
        ClubGroup group = groupService.findById(Long.parseLong(groupId));
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping("/groups")
    public ResponseEntity<ClubGroup> createGroup(@RequestBody ClubGroup group) {
        Club clubObject = null;
        if (group.getClub() != null) {
            clubObject = clubService.findById(group.getClub().getId());
        }
        group.setClub(clubObject);
        ClubGroup savedGroup = groupService.save(group);

        HttpStatus status;
        if (savedGroup != null) {
            status = HttpStatus.CREATED;
        }
        else {
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(savedGroup, status);
    }
}
