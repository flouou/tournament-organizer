package com.bindschaedel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonIgnoreProperties({ "groups" })
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Club extends BaseEntity {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String city;

    public Club(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "club", orphanRemoval = true)
    @Getter
    @Setter
    private List<ClubGroup> groups = new ArrayList<>();
}
