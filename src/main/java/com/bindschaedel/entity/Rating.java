package com.bindschaedel.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Rating extends BaseEntity {

    @DecimalMin(value = "1.0")
    double value;

    @ManyToOne
    @JoinColumn(name = "show_rating_id", referencedColumnName = "ID")
    ShowRating showRating;

    public Rating(double value) {
        this.value = value;
    }
}
