package com.bindschaedel.Service;

import com.bindschaedel.Entity.Club;
import com.bindschaedel.Repository.ClubRepository;
import org.springframework.stereotype.Service;


@Service
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Iterable<Club> getAll(){
        return clubRepository.findAll();
    }
}
