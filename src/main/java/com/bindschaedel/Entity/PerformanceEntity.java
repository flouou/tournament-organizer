package com.bindschaedel.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PerformanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
