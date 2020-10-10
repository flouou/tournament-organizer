package com.bindschaedel.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ClubGroup extends BaseEntity {

    @NotNull
    @NotBlank
    private String        name;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "classification_id", referencedColumnName = "ID")
    @Setter
    private Classification classification;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "ID")
    @Setter
    private Club club;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group", orphanRemoval = true)
    @Setter
    private ShowRating showRating = new ShowRating();

    public ClubGroup(String name) {
        this.name = name;
    }
}
