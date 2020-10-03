package com.bindschaedel.service;

import com.bindschaedel.annotations.IntegrationTest;
import com.bindschaedel.entity.Club;
import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.entity.Rating;
import com.bindschaedel.entity.ShowRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class GroupServiceTest {

    @Autowired
    GroupService groupService;
    @Autowired
    ClubService  clubService;

    Club club;

    @BeforeEach
    void eachSetup() {
        club = new Club("Test Club", "Test City");
        clubService.save(club);
    }

    @Test
    void canAddRatingToGroup() {
        ClubGroup group = new ClubGroup();
        group.setName("Test Group");
        group.setClub(clubService.findByName("test").get(0));
        group.setAge("5-8");
        Rating rating1 = new Rating(1);
        Rating rating2 = new Rating(5);
        Rating rating3 = new Rating(6);
        group.setShowRating(new ShowRating(Arrays.asList(rating1, rating2, rating3)));
        ClubGroup savedGroup = groupService.save(group);
        assertThat(savedGroup.getShowRating()).isNotNull();
        assertThat(savedGroup.getShowRating().getRatings().get(0).getValue()).isEqualTo(1);
    }
}
