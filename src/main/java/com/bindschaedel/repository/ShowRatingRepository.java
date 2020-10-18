package com.bindschaedel.repository;

import com.bindschaedel.entity.ShowRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRatingRepository extends JpaRepository<ShowRating,Long> {

    void deleteByGroupId(Long id);
}
