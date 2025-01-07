package com.ninedocs.userserver.user.application.emailverificationcode;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationCodeRepository {

  private final RedisTemplate<String, String> redisTemplate;

  // 검증 코드를 Redis에 저장
  public LocalDateTime saveVerificationCode(String email, String code) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime expiredAt = now.plusMinutes(5);
    redisTemplate.opsForValue().set(email, code, 6, TimeUnit.MINUTES); // 5분 TTL
    return expiredAt;
  }
}
