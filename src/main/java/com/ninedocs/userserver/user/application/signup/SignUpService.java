package com.ninedocs.userserver.user.application.signup;

import com.ninedocs.userserver.user.application.emailverification.exception.EmailVerificationException;
import com.ninedocs.userserver.user.application.emailverificationcode.EmailFormatValidator;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.EmailFormatException;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

  private final EmailVerificationChecker emailVerificationChecker;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public long signUp(String email, String password, String nickname) {
    if (!EmailFormatValidator.isValid(email)) {
      throw new EmailFormatException();
    }
    if (!emailVerificationChecker.checkEmailVerification(email)) {
      throw new EmailVerificationException();
    }
    String hashedPassword = bCryptPasswordEncoder.encode(password);
    User user = User.builder()
        .email(email)
        .password(hashedPassword)
        .nickname(nickname)
        .build();
    return userRepository.save(user).getId();

  }

}
