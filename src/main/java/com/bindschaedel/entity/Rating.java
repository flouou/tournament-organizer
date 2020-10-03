package com.bindschaedel.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Rating extends BaseEntity {

    @ManyToOne(cascade =
            { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }
    )
    @JoinColumn(name = "show_rating_id", referencedColumnName = "ID")
    private ShowRating showRating;
}
