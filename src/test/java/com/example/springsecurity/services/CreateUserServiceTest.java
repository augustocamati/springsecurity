package com.example.springsecurity.services;

import com.example.springsecurity.dto.CreateUserRequestDto;
import com.example.springsecurity.dto.CreateUserResponseDto;
import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.repositories.RoleRepository;
import com.example.springsecurity.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private RoleRepository roleRepository;

  @Mock
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @InjectMocks
  private CreateUserService createUserService;

  private Role basicRole;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    basicRole = new Role();
    basicRole.setName(Role.Values.BASIC.name());
  }

  @Test
  void shouldCreateUserSuccessfully() {
    // Arrange
    CreateUserRequestDto request = new CreateUserRequestDto("john_doe", "123456");

    when(userRepository.findByUsername("john_doe")).thenReturn(Optional.empty());
    when(roleRepository.findByName(Role.Values.BASIC.name())).thenReturn(basicRole);
    when(bCryptPasswordEncoder.encode("123456")).thenReturn("encoded_password");

    // Act
    CreateUserResponseDto response = createUserService.execute(request);

    // Assert
    assertNotNull(response);
    assertEquals("john_doe", response.username());

    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  void shouldThrowExceptionWhenUsernameAlreadyExists() {
    // Arrange
    CreateUserRequestDto request = new CreateUserRequestDto("john_doe", "123456");
    User existingUser = new User();
    existingUser.setUsername("john_doe");

    when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(existingUser));

    // Act & Assert
    doThrow(new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists"))
        .when(userRepository).save(any(User.class));

    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
      createUserService.execute(request);
    });

    assertEquals("422 UNPROCESSABLE_ENTITY \"Username already exists\"", exception.getMessage());

    verify(userRepository, never()).save(any(User.class));
  }
}
