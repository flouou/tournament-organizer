package com.bindschaedel.Repository;

import com.bindschaedel.Entity.Club;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends CrudRepository<Club, Long> {

    List<Club> findByNameContainingIgnoreCase(String title);
}
