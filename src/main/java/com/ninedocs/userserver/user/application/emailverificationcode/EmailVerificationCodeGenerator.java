package com.ninedocs.userserver.user.application.emailverificationcode;

import com.ninedocs.userserver.user.persistence.UserRepository;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationCodeGenerator {

  public String generateVerificationCode() {
    // return String.valueOf((int) (Math.random() * 900000) + 100000); // 6자리 랜덤 숫자
    return "111111";
  }
}
