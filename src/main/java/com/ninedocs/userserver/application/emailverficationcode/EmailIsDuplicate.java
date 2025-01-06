package com.ninedocs.userserver.application.emailverficationcode;

import com.ninedocs.userserver.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailIsDuplicate {

  private final UserRepository userRepository;

  public Boolean isDuplicateEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
}
