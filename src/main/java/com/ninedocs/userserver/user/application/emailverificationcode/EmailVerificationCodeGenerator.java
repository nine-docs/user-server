package com.ninedocs.userserver.user.application.emailverificationcode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationCodeGenerator {

  public String generateVerificationCode() {
     return String.valueOf((int) (Math.random() * 900000) + 100000); // 6자리 랜덤 숫자
  }
}
