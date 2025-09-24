package com.example.springsecurity.services;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.springsecurity.dto.CreateTweetRequestDto;
import com.example.springsecurity.dto.CreateTweetResponseDto;
import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.Tweet;
import com.example.springsecurity.repositories.TweetRepository;
import com.example.springsecurity.repositories.UserRepository;

@Service
public class DeleteTweetService {

  private final TweetRepository tweetRepository;
  private final UserRepository userRepository;

  public DeleteTweetService(TweetRepository tweetRepository, UserRepository userRepository) {
    this.tweetRepository = tweetRepository;
    this.userRepository = userRepository;
  }

  public ResponseEntity<Void> execute(@PathVariable("id") Long tweetId,
      JwtAuthenticationToken token) {
var user = userRepository.findById(UUID.fromString(token.getName()));
        var tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (isAdmin || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            tweetRepository.deleteById(tweetId);

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


        return ResponseEntity.ok().build();
  }

}
