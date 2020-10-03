package com.bindschaedel.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ClubGroup extends BaseEntity {

    private String        name;
    private String        age;
    private LocalDateTime time;

    @ManyToOne(cascade =
            { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }
    )
    @JoinColumn(name = "club_id", referencedColumnName = "ID")
    private Club club;
}
