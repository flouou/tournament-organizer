package com.bindschaedel.service;

import com.bindschaedel.annotations.IntegrationTest;
import com.bindschaedel.entity.Club;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@IntegrationTest
public class ClubServiceTest {

    @Autowired
    ClubService clubService;

    Club club;

    @BeforeEach
    public void setup() {
        prepareClub();
    }

    private void prepareClub() {
        club = new Club("Mein Verein", "Minze");
    }

    @AfterEach
    public void clean() {
        clubService.removeAll();
    }

    @Test
    void canSearchAllClubs() {
        assertThat(Iterables.size(clubService.getAll())).isZero();
    }

    @Test
    void canSearchAllClubsWithResult() {
        clubService.save(club);
        assertThat(Iterables.size(clubService.getAll())).isOne();
    }

    @Test
    void canFindById() {
        Club savedClub = clubService.save(club);
        assertThat(clubService.findById(savedClub.getId())).isNotNull();
        assertThat(clubService.findById(savedClub.getId()).getName()).isEqualTo(club.getName());
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

    @Test
    void clubWithoutCityIsNotSaved() {
        Club invalidClub = new Club();
        invalidClub.setName("Test Verein");
        assertThatExceptionOfType(TransactionSystemException.class).isThrownBy(() -> clubService.save(invalidClub));
        invalidClub.setCity("");
        assertThatExceptionOfType(TransactionSystemException.class).isThrownBy(() -> clubService.save(invalidClub));
    }

    @Test
    void clubWithoutNameIsNotSaved() {
        Club invalidClub = new Club();
        invalidClub.setCity("Test City");
        assertThatExceptionOfType(TransactionSystemException.class).isThrownBy(() -> clubService.save(invalidClub));
        invalidClub.setName("");
        assertThatExceptionOfType(TransactionSystemException.class).isThrownBy(() -> clubService.save(invalidClub));
    }

    @Test
    void canRemoveClub() {
        Club savedClub = clubService.save(club);
        clubService.remove(savedClub.getId());
        assertThat(clubService.count()).isZero();
    }
}
