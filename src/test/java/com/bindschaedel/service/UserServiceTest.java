package com.bindschaedel.service;

import com.bindschaedel.annotations.IntegrationTest;
import com.bindschaedel.entity.User;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @AfterEach
    public void clean() {
        userService.removeAll();
    }

    @Test
    public void testSignUp() {
        User user = new User("Username", "Userpassword");
        userService.signUp(user);
        assertThat(Iterables.size(userService.getAll())).isOne();
    }
}
