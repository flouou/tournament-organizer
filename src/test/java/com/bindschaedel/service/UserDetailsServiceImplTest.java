package com.bindschaedel.service;

import com.bindschaedel.annotations.IntegrationTest;
import com.bindschaedel.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@IntegrationTest
public class UserDetailsServiceImplTest {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserService userService;

    @AfterEach
    public void cleanup() {
        userService.removeAll();
    }

    @Test
    public void testLoadUserByUsername() {
        User user = new User("Username", "Userpassword");
        userService.signUp(user);
        assertThat(userDetailsService.loadUserByUsername(user.getUsername()).getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void testLoadInvalidUserByUsername() {
        assertThatExceptionOfType(UsernameNotFoundException.class).isThrownBy(() -> userDetailsService.loadUserByUsername("wrong"));
    }
}
