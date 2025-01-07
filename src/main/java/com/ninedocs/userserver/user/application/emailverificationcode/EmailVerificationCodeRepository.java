package com.ninedocs.userserver.user.application.emailverificationcode;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationCodeRepository {

  private final RedisTemplate<String, String> redisTemplate;

  // 검증 코드를 Redis에 저장
  public long saveVerificationCode(String email, String code) {
    redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES); // 5분 TTL
    return redisTemplate.getExpire(email, TimeUnit.MINUTES);
  }
}
