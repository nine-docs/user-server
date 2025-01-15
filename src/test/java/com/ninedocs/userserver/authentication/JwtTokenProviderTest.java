package com.ninedocs.userserver.authentication;

import com.ninedocs.userserver.user.application.signin.JwtProvider;
import com.ninedocs.userserver.user.application.signin.dto.JwtTokenResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtTokenProviderTest {

  private JwtProvider jwtProvider;

  @BeforeEach
  void setUp() {
    // 테스트용 비밀 키
    String secret = "mySuperSecretKey12345678901234567890mySuperSecretKey1234567890123456";
    // 1시간 (3600000ms)
    long expirationMs = 3600000;
    jwtProvider = new JwtProvider(secret, expirationMs);
  }

  @Test
  void generateAccessToken_ShouldReturnValidToken() {
    long userId = 123;

    JwtTokenResult tokenResult = jwtProvider.generateAccessToken(userId);

    Assertions.assertNotNull(tokenResult);
    Assertions.assertNotNull(tokenResult.getToken());
    Assertions.assertNotNull(tokenResult.getExpiredAt());
  }
}