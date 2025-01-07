package com.ninedocs.userserver.user.application.emailverificationcode;

import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailDuplicationChecker {

  private final UserRepository userRepository;

  public Boolean isDuplicateEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
}
