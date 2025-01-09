package com.ninedocs.userserver.user.application.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncryptor {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public String hashedPassword(String password) {
    return bCryptPasswordEncoder.encode(password);
  }

  public boolean matches(String password, String hashedPassword) {
    return bCryptPasswordEncoder.matches(password, hashedPassword);
  }

}
