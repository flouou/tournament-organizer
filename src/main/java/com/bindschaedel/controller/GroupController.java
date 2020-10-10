package com.bindschaedel.controller;

import com.bindschaedel.entity.Classification;
import com.bindschaedel.entity.Club;
import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.service.ClassificationService;
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
public class GroupController {

    private final GroupService          groupService;
    private final ClubService           clubService;
    private final ClassificationService classificationService;

    public GroupController(GroupService groupService, ClubService clubService, ClassificationService classificationService) {
        this.groupService = groupService;
        this.clubService = clubService;
        this.classificationService = classificationService;
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
        if (group != null && group.getClub() != null) {
            Club clubObject = clubService.findById(group.getClub().getId());
            group.setClub(clubObject);
        }

        ClubGroup savedGroup = groupService.save(group);

        HttpStatus status = savedGroup == null ? HttpStatus.CONFLICT : HttpStatus.CREATED;
        return new ResponseEntity<>(savedGroup, status);
    }

    @PostMapping("/groups/{groupId}/classification/{classificationId}")
    public ResponseEntity<ClubGroup> updateGroupClassification(@PathVariable(value = "groupId") String groupId,
                                                               @PathVariable(value = "classificationId") String classificationId) {
        ClubGroup group = groupService.findById(Long.parseLong(groupId));
        Classification classification = classificationService.findById(Long.parseLong(classificationId));
        if (group == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else if (classification == null) {
            return new ResponseEntity<>(group, HttpStatus.CONFLICT);
        }
        else {
            group.setClassification(classification);
            ClubGroup savedGroup = groupService.save(group);
            return new ResponseEntity<>(savedGroup, HttpStatus.OK);
        }
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable(value = "groupId") String groupId) {
        if (groupService.findById(Long.parseLong(groupId)) != null) {
            groupService.remove(Long.parseLong(groupId));
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
    }
}
