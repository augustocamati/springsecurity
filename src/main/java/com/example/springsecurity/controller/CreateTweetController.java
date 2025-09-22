package com.example.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springsecurity.dto.CreateTweetRequestDto;
import com.example.springsecurity.dto.CreateTweetResponseDto;
import com.example.springsecurity.services.CreateTweetService;

@Controller

public class CreateTweetController {
  private final CreateTweetService createTweetService;

  public CreateTweetController(CreateTweetService createTweetService) {
    this.createTweetService = createTweetService;
  }

  @PostMapping("/tweets")
  public ResponseEntity<CreateTweetResponseDto> create(@RequestBody CreateTweetRequestDto createTweetRequestDto, 
      JwtAuthenticationToken token) {

    return createTweetService.execute(createTweetRequestDto, token);
  }

}
