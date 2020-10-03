package com.bindschaedel.Controller;

import com.bindschaedel.Entity.ClubGroup;
import com.bindschaedel.Service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public ResponseEntity<Iterable<ClubGroup>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<ClubGroup> getGroupById(@PathVariable(value = "groupId") String groupId) {
        ClubGroup group = groupService.findById(Long.parseLong(groupId));
        return new ResponseEntity<>(group, HttpStatus.ACCEPTED);
    }
}
