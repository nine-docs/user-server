package com.ninedocs.userserver.signUp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import com.ninedocs.userserver.user.application.signup.PasswordEncrypter;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncrypterTest {

  @Mock
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @InjectMocks
  PasswordEncrypter passwordEncrypter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testEncryptPassword() {
    final String password = "password";
    final String hashedPassword = "hashedPassword";
    when(bCryptPasswordEncoder.encode(password)).thenReturn(hashedPassword);

    String result = passwordEncrypter.hashedPassword(password);
    assertEquals(hashedPassword, result, "Incorrect hashed password");
  }
}
