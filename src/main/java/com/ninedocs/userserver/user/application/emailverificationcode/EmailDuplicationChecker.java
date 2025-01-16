package com.ninedocs.userserver.user.application.emailverificationcode;

import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EmailDuplicationChecker {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public Boolean isDuplicateEmail(String email) {
    // 일단은 탈퇴한 email 로 재가입 불가능하게 해놓음
    return userRepository.findByEmail(email)
        .isPresent();
  }
}
