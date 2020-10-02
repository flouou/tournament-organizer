package com.bindschaedel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private Long id;
    private String name;
    private String city;
}
