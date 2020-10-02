package com.bindschaedel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@JsonIgnoreProperties( {"groups"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private Long id;
    private String name;
    private String city;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "club")
    @Setter
    private Set<ClubGroup> groups = new HashSet<>();
}
