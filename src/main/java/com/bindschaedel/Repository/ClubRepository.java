package com.bindschaedel.Repository;

import com.bindschaedel.Entity.ClubEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClubRepository extends CrudRepository<ClubEntity, Integer> {

    @Transactional(readOnly = false)
    @Modifying
    @Query(value="UPDATE ClubEntity c SET c.clubOrder =:clubOrder where c.id=:id")
    void updateClubOrder(@Param("clubOrder") Integer clubOrder, @Param("id") Integer id);
}
