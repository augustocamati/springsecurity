package com.example.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springsecurity.dto.LoginRequestDto;
import com.example.springsecurity.dto.LoginResponseDto;
import com.example.springsecurity.services.LoginService;

@Controller
@RequestMapping("/auth")
public class LoginController {

  private  LoginService loginService;
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {


  //  return ResponseEntity.ok("Login successful");
    return loginService.execute(loginRequestDto);
  }

}
