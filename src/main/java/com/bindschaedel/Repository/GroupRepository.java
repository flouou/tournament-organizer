package com.bindschaedel.Repository;

import com.bindschaedel.Entity.ClubGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<ClubGroup, Long> {

    public List<ClubGroup> findByClubId(Long id);
}
