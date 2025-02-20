package com.ninedocs.userserver.user.application.emailverificationcode;

import org.junit.jupiter.api.Test;

class EmailVerificationCodeGeneratorTest {

  @Test
  void generateVerificationCode() {
    System.out.println("# " + new EmailVerificationCodeGenerator().generateVerificationCode());
  }
}
