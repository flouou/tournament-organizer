package com.bindschaedel.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User extends BaseEntity {

    @Getter
    private String username;
    @Setter
    @Getter
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
