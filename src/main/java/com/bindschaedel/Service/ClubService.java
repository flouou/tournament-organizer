package com.bindschaedel.Service;

import com.bindschaedel.Entity.Club;
import com.bindschaedel.Repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClubService {

    @Autowired
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Iterable<Club> getAll() {
        return clubRepository.findAll();
    }

    public Club findById(Long id) {
        Optional<Club> clubOptional = clubRepository.findById(id);
        return clubOptional.orElseGet(Club::new);
    }
}
