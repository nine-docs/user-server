package com.ninedocs.userserver.application.emailverificationcode;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class EmailVerificationCode {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  public String generateVerificationCode() {
    // return String.valueOf((int) (Math.random() * 900000) + 100000); // 6자리 랜덤 숫자
    return "111111";
  }

  // 검증 코드를 Redis에 저장
  public long saveVerificationCode(String email, String code) {
    long startTime = System.currentTimeMillis(); // 현재 시간 기록
    redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES); // 5분 TTL
    return startTime;
  }

}
