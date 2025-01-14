package com.ninedocs.userserver.user.application.userprofile;

import com.ninedocs.userserver.user.application.deleteuser.exception.NullUserException;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

  private final UserRepository userRepository;

  public UserProfileResponse UserCheck(long id) {
    if (userRepository.findById(id).isEmpty()) {
      throw new NullUserException();
    }
    User user = userRepository.findById(id).get();
    return new UserProfileResponse(user.getEmail(),
        user.getNickname());
  }

}
