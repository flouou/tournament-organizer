package com.bindschaedel.controller;

import com.bindschaedel.entity.Club;
import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.service.ClubService;
import com.bindschaedel.service.GroupService;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClubControllerTest {

    @InjectMocks
    ClubController clubController;

    @Mock ClubService  clubService;
    @Mock GroupService groupService;

    @Test
    public void testGetSingleClub() {
        Club club = new Club("Test Club", "Test City");
        when(clubService.findById(any(Long.class))).thenReturn(club);
        ResponseEntity<Club> responseEntity = clubController.getSingleClub("1");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getName()).isEqualTo(club.getName());
    }

    @Test
    public void testGetAllClubs() {
        Club club1 = new Club("Test Club", "Test City");
        Club club2 = new Club("Second Test Club", "Another City");
        Iterable<Club> clubs = Arrays.asList(club1, club2);
        when(clubService.getAll()).thenReturn(clubs);
        ResponseEntity<Iterable<Club>> responseEntity = clubController.getAllClubs();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(Iterables.size(responseEntity.getBody())).isEqualTo(2);
    }

    @Test
    public void testGetGroupsForClubId() {
        Club club1 = new Club("Test Club", "Test City");
        ClubGroup group = new ClubGroup();
        group.setName("TestGroup");
        group.setAge("5-8");
        group.setClub(club1);
        when(groupService.findGroupByClubId(any(Long.class))).thenReturn(Collections.singletonList(group));
        ResponseEntity<Iterable<ClubGroup>> responseEntity = clubController.getGroupsForClub("1");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(Iterables.size(responseEntity.getBody())).isEqualTo(1);
    }

    @Test
    public void testCreateClub() {
        Club club = new Club("Test Club", "Test City");
        when(clubService.save(any(Club.class))).thenReturn(club);
        ResponseEntity<Club> responseEntity = clubController.createClub(club);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getName()).isEqualTo(club.getName());
    }

    @Test
    public void testCreateInvalidClub() {
        when(clubService.save(null)).thenReturn(null);
        ResponseEntity<Club> responseEntity = clubController.createClub(null);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(409);
        assertThat(responseEntity.getBody()).isNull();
    }
}
