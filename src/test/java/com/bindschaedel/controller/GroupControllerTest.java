package com.bindschaedel.controller;

import com.bindschaedel.entity.Club;
import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.service.ClubService;
import com.bindschaedel.service.GroupService;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {

    @InjectMocks
    GroupController groupController;

    @Mock GroupService groupService;
    @Mock ClubService  clubService;

    Club club;

    @BeforeEach
    public void setup() {
        club = new Club("Test Club", "Test City");
    }

    @Test
    public void testGetSingleGroup() {
        ClubGroup group = new ClubGroup();
        group.setName("Test Group");
        group.setAge("5-8");
        when(groupService.findById(any(Long.class))).thenReturn(group);
        ResponseEntity<ClubGroup> responseEntity = groupController.getGroupById("1");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getName()).isEqualTo(group.getName());
    }

    @Test
    public void testGetAllGroups() {
        ClubGroup group = new ClubGroup();
        group.setName("Test Group");
        group.setAge("5-8");
        ClubGroup group2 = new ClubGroup();
        group2.setName("Second Test Group");
        group2.setAge("5-8");
        Iterable<ClubGroup> groups = Arrays.asList(group, group2);
        when(groupService.getAll()).thenReturn(groups);
        ResponseEntity<Iterable<ClubGroup>> responseEntity = groupController.getAllGroups();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(Iterables.size(responseEntity.getBody())).isEqualTo(2);
    }

    @Test
    public void testCreateGroup() {
        ClubGroup group = new ClubGroup();
        group.setName("Test Group");
        group.setClub(club);
        when(groupService.save(any(ClubGroup.class))).thenReturn(group);
        ResponseEntity<ClubGroup> responseEntity = groupController.createGroup(group);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getName()).isEqualTo(group.getName());
    }

    @Test
    public void testCreateNullGroup() {
        when(groupService.save(null)).thenReturn(null);
        ResponseEntity<ClubGroup> responseEntity = groupController.createGroup(null);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(409);
        assertThat(responseEntity.getBody()).isNull();
    }
}
