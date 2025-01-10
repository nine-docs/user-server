package com.ninedocs.userserver.user.application.deleteuser;

import com.ninedocs.userserver.user.application.deleteuser.dto.DeleteUserRequest;
import com.ninedocs.userserver.user.application.deleteuser.exception.NullUserException;
import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserService {

  private final UserRepository userRepository;

  public void deleteUser(long id) {
    if (!userRepository.existsById(id)) {
      throw new NullUserException();
    }
    userRepository.deleteById(id);
  }

}
