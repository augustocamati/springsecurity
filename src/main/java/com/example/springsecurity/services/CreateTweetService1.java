package com.example.springsecurity.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springsecurity.dto.CreateTweetRequestDto;
import com.example.springsecurity.dto.CreateTweetResponseDto;
import com.example.springsecurity.entities.Tweet;
import com.example.springsecurity.repositories.TweetRepository;
import com.example.springsecurity.repositories.UserRepository;

@Service
public class CreateTweetService1 {

  private final TweetRepository tweetRepository;
  private final UserRepository userRepository;

  public CreateTweetService1(TweetRepository tweetRepository, UserRepository userRepository) {
    this.tweetRepository = tweetRepository;
    this.userRepository = userRepository;
  }

  public ResponseEntity<CreateTweetResponseDto> execute(@RequestBody CreateTweetRequestDto createTweetRequestDto,
      JwtAuthenticationToken token) {

    var user = userRepository.findById(UUID.fromString(token.getName()))
        .orElseThrow(() -> new RuntimeException("User not found"));

    var tweet = new Tweet();

    tweet.setUser(user);
    tweet.setContent(createTweetRequestDto.content());

    tweetRepository.save(tweet);

    return ResponseEntity.ok(new CreateTweetResponseDto(tweet));
  }

}
