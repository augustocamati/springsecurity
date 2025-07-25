package com.example.springsecurity.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurity.entities.Tweet;

import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    // Additional query methods can be defined here if needed
  
}
