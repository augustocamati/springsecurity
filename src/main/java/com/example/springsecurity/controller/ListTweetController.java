package com.example.springsecurity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// Update the import path to the correct location of ListTweetService

import com.example.springsecurity.entities.Tweet;
import com.example.springsecurity.services.ListTweetService;

@Controller

public class ListTweetController {
  private ListTweetService listTweetService;

  public ListTweetController(ListTweetService listTweetService) {
    this.listTweetService = listTweetService;
  }

  @GetMapping("/tweets")
  
  public ResponseEntity<List<Tweet>> list() {
    List<Tweet> tweets = listTweetService.execute();
 
    return ResponseEntity.ok(tweets);
  }

}
