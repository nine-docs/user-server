package com.ninedocs.userserver.user.application.userprofile;

import com.ninedocs.userserver.user.application.deleteuser.exception.NullUserException;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileQueryService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public UserProfileResponse getUserProfile(long id) {
    User user = userRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(NullUserException::new);

    return new UserProfileResponse(user.getEmail(), user.getNickname());
  }
}
