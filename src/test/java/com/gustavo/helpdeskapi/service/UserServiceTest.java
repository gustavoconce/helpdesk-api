package com.gustavo.helpdeskapi.service;

import com.gustavo.helpdeskapi.dto.UserCreateDTO;
import com.gustavo.helpdeskapi.entity.User;
import com.gustavo.helpdeskapi.exception.ResourceNotFoundException;
import com.gustavo.helpdeskapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import com.gustavo.helpdeskapi.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindUserById() {

        User user = new User();

        user.setId(1L);
        user.setName("Gustavo");
        user.setEmail("gustavo@email.com");
        user.setPassword("123456");
        user.setRole("USER");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Gustavo", result.getName());
        Assertions.assertEquals("gustavo@email.com", result.getEmail());
        Assertions.assertEquals("USER", result.getRole());
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {

        when(userRepository.findById(999L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(999L)
        );
    }

    @Test
    void shouldCreateUser() {

        UserCreateDTO dto = new UserCreateDTO(
                "Gustavo",
                "gustavo@email.com",
                "123456",
                "USER"
        );

        User savedUser = new User();

        savedUser.setId(2L);
        savedUser.setName("Gustavo");
        savedUser.setEmail("gustavo@email.com");
        savedUser.setPassword("123456");
        savedUser.setRole("USER");

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        UserDTO result = userService.createUser(dto);

        Assertions.assertEquals(2L, result.getId());
        Assertions.assertEquals("Gustavo", result.getName());
        Assertions.assertEquals("gustavo@email.com", result.getEmail());
        Assertions.assertEquals("USER", result.getRole());

        verify(userRepository).save(any(User.class));
    }
}