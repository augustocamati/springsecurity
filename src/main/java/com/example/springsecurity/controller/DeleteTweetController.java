package com.example.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.springsecurity.services.DeleteTweetService;

@Controller

public class DeleteTweetController {
  private DeleteTweetService deleteTweetService;

  public DeleteTweetController(DeleteTweetService deleteTweetService) {
    this.deleteTweetService = deleteTweetService;
  }

  @DeleteMapping("/tweets/{id}")
  public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId,
      JwtAuthenticationToken token) {
    return deleteTweetService.execute(tweetId, token);
  }

}
