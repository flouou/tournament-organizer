package com.bindschaedel.service;

import com.bindschaedel.entity.Club;
import com.bindschaedel.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

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

    public List<Club> findByName(String name) {
        return clubRepository.findByNameContainingIgnoreCase(name);
    }

    public Club save(Club club) {
        if (club != null) {
            return clubRepository.save(club);
        }
        else {
            return null;
        }
    }

    public long count() {
        return clubRepository.count();
    }
}
