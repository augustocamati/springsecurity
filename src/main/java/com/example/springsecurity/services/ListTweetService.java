package com.example.springsecurity.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springsecurity.entities.Tweet;
import com.example.springsecurity.repositories.TweetRepository;

@Service
public class ListTweetService {
  private final TweetRepository tweetRepository;

  public ListTweetService(TweetRepository tweetRepository) {
    this.tweetRepository = tweetRepository;

  }

  public List<Tweet> execute() {
    return tweetRepository.findAll();
  }
}
