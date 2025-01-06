package com.ninedocs.userserver.application.emailverificationcode;

import com.ninedocs.userserver.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class EmailIsDuplicate {

  private final UserRepository userRepository;

  public Boolean isDuplicateEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
}
