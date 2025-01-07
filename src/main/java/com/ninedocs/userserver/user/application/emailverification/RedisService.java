package com.ninedocs.userserver.user.application.emailverification;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisService {

  private final StringRedisTemplate stringRedisTemplate;

  public String getVerificationCode(String email) {
    return stringRedisTemplate.opsForValue().get("verified-code" + email);
  }

  public void storeVerifiedCode(String email, long verifiedExpiration) {
    stringRedisTemplate.opsForValue()
        .set("verified" + email, "verified", verifiedExpiration, TimeUnit.MINUTES);
  }

  public void deleteVerificationCode(String email) {
    stringRedisTemplate.delete(email);
  }
}
