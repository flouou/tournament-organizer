package com.bindschaedel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
/*@JsonIgnoreProperties({ "group", "ratings" })*/
@JsonIgnoreProperties({ "group" })
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ShowRating extends BaseEntity implements Serializable {

    @OneToOne(cascade =
            { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }
    )
    @JoinColumn(name = "group_id", referencedColumnName = "ID")
    private ClubGroup group;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "show_rating_id")
    @Setter
    private List<Rating> ratings = new ArrayList<>();

    public ShowRating(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
