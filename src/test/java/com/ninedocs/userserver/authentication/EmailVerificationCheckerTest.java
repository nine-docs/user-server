package com.ninedocs.userserver.authentication;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ninedocs.userserver.user.application.signup.EmailVerificationChecker;
import com.ninedocs.userserver.user.application.signup.dto.SignUpRequest;
import com.ninedocs.userserver.user.application.signup.exception.EmailNotVerifiedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

class EmailVerificationCheckerTest {

  @Mock
  private StringRedisTemplate stringRedisTemplate;

  @Mock
  private ValueOperations<String, String> valueOperations;

  @InjectMocks
  private EmailVerificationChecker emailVerificationChecker;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    // 모의 객체에 opsForValue() 호출 결과 설정
    when(stringRedisTemplate.opsForValue()).thenReturn(valueOperations);
  }

  @Test
  void checkEmailVerificationTrue() {
    // Given
    String email = "test@example.com";
    String key = "verified-" + email;
    when(valueOperations.get(key)).thenReturn("verified");

    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.setEmail(email);

    // When
    boolean result = emailVerificationChecker.checkEmailVerification(email);

    // Then
    assertTrue(result);
  }

  @Test
  void checkEmailVerificationFalse() {
    // Given
    String email = "test@example.com";
    String key = "verified-" + email;
    when(valueOperations.get(key)).thenReturn(null);

    // When & Then
    assertThrows(EmailNotVerifiedException.class, () ->
        emailVerificationChecker.checkEmailVerification(email)
    );
  }
}
