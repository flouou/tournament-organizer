package com.bindschaedel.repository;

import com.bindschaedel.entity.ClubGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<ClubGroup,Long> {
    List<ClubGroup> findByClubId(Long id);
}
