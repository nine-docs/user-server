package com.ninedocs.userserver.user.application.signin;

import com.ninedocs.userserver.user.application.signin.exception.SignInFailException;
import com.ninedocs.userserver.user.application.signup.PasswordEncrypter;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInService {

  private final JwtProvider jwtProvider;
  private final UserRepository userRepository;
  private final PasswordEncrypter passwordEncrypter;

  public boolean signIn(String email, String password) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty()) {
      throw new SignInFailException();
    }
    

  }

}
