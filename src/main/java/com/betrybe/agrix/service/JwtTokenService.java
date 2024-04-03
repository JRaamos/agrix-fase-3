package com.betrybe.agrix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;




/**
 * Provides operations for JWT token generation and validation.
 */
@Service
public class JwtTokenService {

  private final Algorithm signingAlgorithm;

  public JwtTokenService(@Value("${api.security.token.secret}") String secret) {
    this.signingAlgorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Metodo para criar o token.
   */
  public String createTokenForUser(String userName) {
    return JWT.create()
        .withSubject(userName)
        .withExpiresAt(generateExpiration())
        .sign(signingAlgorithm);
  }

  /**
   * Quando o tenken expira.
   */
  private Instant generateExpiration() {
    return Instant.now()
        .plus(2, ChronoUnit.HOURS);
  }

  /**
   * Metodo para buscar usuario pelo token.
   */
  public String getUserIdFromToken(String token) {
    return JWT.require(signingAlgorithm)
        .build()
        .verify(token)
        .getSubject();
  }
}
