package com.ninedocs.userserver.user.application.signin;

import com.ninedocs.userserver.user.application.signin.exception.SignInFailException;
import com.ninedocs.userserver.user.application.signup.PasswordEncryptor;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInService {

  private final UserRepository userRepository;
  private final PasswordEncryptor passwordEncryptor;
  private final JwtProvider jwtProvider;

  public long signIn(String email, String password) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty()) {
      throw new SignInFailException();
    }
    if (!passwordEncryptor.matches(password, user.get().getPassword())) {
      throw new SignInFailException();
    }

    return user.get().getId();
  }

}
