package com.ninedocs.userserver.user.application.userprofile;

import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.application.userprofile.exception.UserNotExistException;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileUpdateService {

  private final UserRepository userRepository;

  @Transactional
  public UserProfileResponse updateNickname(Long userId, String nickname) {
    User user = userRepository.findByIdAndDeletedAtIsNull(userId)
        .orElseThrow(UserNotExistException::new);
    user.setNickname(nickname);
    userRepository.save(user);
    return new UserProfileResponse(user.getEmail(), user.getNickname());
  }
}
