package com.ninedocs.userserver.user.application.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

  private final PasswordEncoder passwordEncoder;

  public String getHashedPassword(String password) {
    return passwordEncoder.encode(password);
  }
}
