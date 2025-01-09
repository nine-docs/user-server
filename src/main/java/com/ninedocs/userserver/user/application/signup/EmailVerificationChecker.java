package com.ninedocs.userserver.user.application.signup;

import com.ninedocs.userserver.user.application.signup.dto.SignUpRequest;
import com.ninedocs.userserver.user.application.signup.exception.EmailNotVerifiedException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationChecker {

  private final StringRedisTemplate stringRedisTemplate;

  public boolean checkEmailVerification(String email) {
    String check = stringRedisTemplate.opsForValue().get("verified-" + email);
    if (!Objects.equals(check, "verified")) {
      throw new EmailNotVerifiedException();
    }
    return true;
  }

}
