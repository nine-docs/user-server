package com.ninedocs.userserver.authentication;

import static org.mockito.Mockito.when;

import com.ninedocs.userserver.user.application.emailverificationcode.EmailVerificationCodeRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@ExtendWith(MockitoExtension.class)
public class EmailVerificationCodeTest {

  @Mock
  private RedisTemplate<String, String> redisTemplate;

  @Mock
  private ValueOperations<String, String> valueOperations;

  private EmailVerificationCodeRepository emailVerificationCodeRepository;

  @BeforeEach
  void setUp() {
    emailVerificationCodeRepository = new EmailVerificationCodeRepository(redisTemplate);
  }

  @Test
  public void checkEmailVerificationCodeInRedis() {
    when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    String email = "example@example.com";
    String code = "111111";

    LocalDateTime expiredAt = emailVerificationCodeRepository.saveVerificationCode(email, code);

    Assertions.assertTrue(expiredAt.isAfter(LocalDateTime.now()),
        "Expiration time should be in the future");
  }
}
