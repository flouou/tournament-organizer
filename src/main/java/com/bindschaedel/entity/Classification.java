package com.bindschaedel.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Classification extends BaseEntity {

    private String name;
    private String description;
}
