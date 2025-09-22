package com.example.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springsecurity.dto.CreateUserRequestDto;
import com.example.springsecurity.dto.CreateUserResponseDto;
import com.example.springsecurity.services.CreateUserService;


@Controller

public class CreateUserController {
  private CreateUserService createUserService;


  public CreateUserController(CreateUserService createUserService) {
    this.createUserService = createUserService;
  }

  @PostMapping("/users")
  public ResponseEntity<CreateUserResponseDto> create(@RequestBody CreateUserRequestDto createUserRequestDto) {
    CreateUserResponseDto responseDto =  createUserService.execute(createUserRequestDto);

      return ResponseEntity.ok(responseDto);
  }
  
}
