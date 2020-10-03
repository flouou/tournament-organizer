package com.bindschaedel.service;

import com.bindschaedel.annotations.IntegrationTest;
import com.bindschaedel.entity.Club;
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
}
