package com.example.springsecurity.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springsecurity.entities.User;
import com.example.springsecurity.repositories.UserRepository;

@Service
public class ListUserService {
private final UserRepository userRepository;


  public ListUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
   
  }

  public List<User> execute() {
    return userRepository.findAll();
  }
}
