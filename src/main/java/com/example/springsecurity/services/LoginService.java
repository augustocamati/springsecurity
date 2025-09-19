package com.example.springsecurity.services;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.example.springsecurity.dto.LoginRequestDto;
import com.example.springsecurity.dto.LoginResponseDto;
import com.example.springsecurity.repositories.UserRepository;

@Service
public class LoginService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtEncoder jwtEncoder;

  public LoginService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
      JwtEncoder jwtEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.jwtEncoder = jwtEncoder;
  }

  public ResponseEntity<LoginResponseDto> execute(LoginRequestDto loginRequestDto) {
    var user = userRepository.findByUsername(loginRequestDto.username());
    if (user.isEmpty() || !bCryptPasswordEncoder.matches(loginRequestDto.password(), user.get().getPassword())) {
      throw new BadCredentialsException("Invalid username or password");
    }

    System.out.println("User authenticated: " + user.get().getUsername());
    var expiresIn = 3600L;
    var now = Instant.now();
    var claims = JwtClaimsSet.builder()
        .issuer("Augusto")
        .subject(user.get().getUserId().toString())
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiresIn))
        .build();

    var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    return ResponseEntity.ok(new LoginResponseDto(token, expiresIn));
  }

}
