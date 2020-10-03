package com.bindschaedel.service;

import com.bindschaedel.annotations.IntegrationTest;
import com.bindschaedel.entity.Club;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class ClubServiceTest {

    @Autowired
    ClubService clubService;

    Club club;

    @BeforeEach
    public void setup() {
        resetClub();
    }

    private void resetClub() {
        club = new Club("Mein Verein", "Minze");
    }

    @Test
    void nullClubNotSaved() {
        clubService.save(null);
        assertThat(clubService.count()).isZero();
    }

    @Test
    void validClubIsSaved() {
        clubService.save(club);
        assertThat(clubService.count()).isOne();
    }
}
