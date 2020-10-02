package com.bindschaedel.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity()
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ClubGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private Long id;
    private String name;
    private String age;

    @ManyToOne(cascade =
            {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinColumn(name = "club_id", referencedColumnName = "ID")
    private Club club;
}
