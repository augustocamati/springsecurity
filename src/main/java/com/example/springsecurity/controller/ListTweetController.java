package com.example.springsecurity.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.springsecurity.dto.ResponseListTweetsDto;
import com.example.springsecurity.services.ListTweetService;

@Controller

public class ListTweetController {
  private ListTweetService listTweetService;

  public ListTweetController(ListTweetService listTweetService) {
    this.listTweetService = listTweetService;
  }

  @GetMapping("/tweets")
  
  public ResponseEntity<List<ResponseListTweetsDto>> list() {
    List<ResponseListTweetsDto> tweets = listTweetService.execute();
 
    return ResponseEntity.ok(tweets);
  }

}
