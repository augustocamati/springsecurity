package com.example.springsecurity.services;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.springsecurity.dto.CreateUserRequestDto;
import com.example.springsecurity.dto.CreateUserResponseDto;
import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.repositories.RoleRepository;
import com.example.springsecurity.repositories.UserRepository;

@Service
public class CreateUserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public CreateUserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public CreateUserResponseDto execute(CreateUserRequestDto createUserRequestDto) {
    var user = userRepository.findByUsername(createUserRequestDto.username());
    var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
    if (user.isPresent()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists");
    }
    var newUser = new User();
    newUser.setUsername(createUserRequestDto.username());
    newUser.setRoles(Set.of(basicRole));
    newUser.setPassword(bCryptPasswordEncoder.encode(createUserRequestDto.password()) );

    userRepository.save(newUser);

    return new CreateUserResponseDto(newUser.getUsername());
  }

}
