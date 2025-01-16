package com.ninedocs.userserver.user.application.signin;

import com.ninedocs.userserver.user.application.signin.exception.SignInFailException;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  @Transactional(readOnly = true)
  public long signIn(String email, String password) {
    Optional<User> user = userRepository.findByEmailAndDeletedAtIsNull(email);
    if (user.isEmpty()) {
      throw new SignInFailException();
    }
    if (!bcryptPasswordEncoder.matches(password, user.get().getPassword())) {
      throw new SignInFailException();
    }

    return user.get().getId();
  }

}
