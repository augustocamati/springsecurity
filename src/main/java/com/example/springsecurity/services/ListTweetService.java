package com.example.springsecurity.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springsecurity.dto.ResponseListTweetsDto;
import com.example.springsecurity.repositories.TweetRepository;

@Service
public class ListTweetService {
  private final TweetRepository tweetRepository;

  public ListTweetService(TweetRepository tweetRepository) {
    this.tweetRepository = tweetRepository;

  }

  public List<ResponseListTweetsDto> execute() {
    List<ResponseListTweetsDto> tweets = tweetRepository.findAll().stream()
        .map(tweet -> new ResponseListTweetsDto(tweet.getId(), tweet.getContent(), tweet.getCreatedAt(),
            tweet.getUser().getUsername()))
        .toList();
    return tweets;
  }
}
