package com.ninedocs.userserver.user.application.userprofile;

import com.ninedocs.userserver.user.application.userprofile.dto.UserPasswordUpdateRequest;
import com.ninedocs.userserver.user.application.userprofile.exception.PasswordNotChangedException;
import com.ninedocs.userserver.user.application.userprofile.exception.UserNotExistException;
import com.ninedocs.userserver.user.application.userprofile.exception.WrongPasswordException;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPasswordUpdateService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  @Transactional
  public void updatePassword(Long userId, UserPasswordUpdateRequest request) {
    User user = userRepository.findByIdAndDeletedAtIsNull(userId)
        .orElseThrow(UserNotExistException::new);

    if (!bcryptPasswordEncoder.matches(request.getOriginalPassword(), user.getPassword())) {
      throw new WrongPasswordException();
    }
    String newPassword = bcryptPasswordEncoder.encode(request.getNewPassword());
    if (bcryptPasswordEncoder.matches(request.getNewPassword(), user.getPassword())) {
      throw new PasswordNotChangedException();
    }
    user.setPassword(newPassword);
  }
}
