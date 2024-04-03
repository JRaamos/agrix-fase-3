package com.betrybe.agrix.controllers;


import com.betrybe.agrix.dtos.LoginRequest;
import com.betrybe.agrix.dtos.LoginResponse;
import com.betrybe.agrix.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenService jwtTokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager,
      JwtTokenService jwtTokenService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenService = jwtTokenService;
  }

  /**
   * Rota post para Login.
   */
  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    String token = jwtTokenService.createTokenForUser(auth.getName());

    return new LoginResponse(token);
  }
}