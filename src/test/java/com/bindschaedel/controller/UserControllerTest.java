package com.bindschaedel.controller;

import com.bindschaedel.entity.User;
import com.bindschaedel.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    public void testSignUp() {
        User user = new User("Username", "UserPassword");
        when(userService.signUp(any(User.class))).thenReturn(user);
        ResponseEntity<User> responseEntity = userController.signUp(user);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void testInvalidSignUp() {
        User user = new User("Username", "");
        ResponseEntity<User> responseEntity = userController.signUp(user);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody()).isNull();

        user = new User(" ", "Password");
        responseEntity = userController.signUp(user);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody()).isNull();
    }
}
