package com.bindschaedel.Entity;

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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ShowRating extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "showRating")
    @Setter
    private Set<Rating> ratings = new HashSet<>();
}
