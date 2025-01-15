package com.ninedocs.userserver.authentication;

import com.ninedocs.userserver.user.application.emailverificationcode.EmailFormatValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class EmailFormatValidationTest {

  @Test
  public void checkEmailFormat() {
    String validEmail = "example@example.com";
    String invalidEmail = "example.com";

    Assertions.assertTrue(EmailFormatValidator.isValid(validEmail));
    Assertions.assertFalse(EmailFormatValidator.isValid(invalidEmail));
  }
}
