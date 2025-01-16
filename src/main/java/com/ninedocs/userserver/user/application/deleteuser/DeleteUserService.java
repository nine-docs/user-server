package com.ninedocs.userserver.user.application.deleteuser;

import com.ninedocs.userserver.user.application.deleteuser.exception.NullUserException;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserService {

  private final UserRepository userRepository;

  @Transactional
  public void deleteUser(long id) {
    User user = userRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(NullUserException::new);

    user.setDeletedAt(LocalDateTime.now());
  }
}
