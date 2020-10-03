package com.bindschaedel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonIgnoreProperties({ "groups" })
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Club extends BaseEntity {

    private String name;
    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
    @Setter
    private Set<ClubGroup> groups = new HashSet<>();
}
