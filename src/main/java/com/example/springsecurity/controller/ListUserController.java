package com.example.springsecurity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springsecurity.entities.User;
import com.example.springsecurity.services.ListUserService;

@Controller

public class ListUserController {
  private ListUserService listUserService;

  public ListUserController(ListUserService listUserService) {
    this.listUserService = listUserService;
  }

  @GetMapping("/users")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<List<User>> list() {
    List<User> users = listUserService.execute();
    System.out.println("List of users: " + users);
    return ResponseEntity.ok(users);
  }

}
