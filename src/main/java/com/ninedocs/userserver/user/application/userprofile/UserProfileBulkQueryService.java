package com.ninedocs.userserver.user.application.userprofile;

import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileBulkQueryService {

  private final UserRepository userRepository;

  /**
   * @return map (key : user ID)
   */
  @Transactional(readOnly = true)
  public Map<Long, UserProfileResponse> getUserProfiles(List<Long> userIds) {
    return userRepository.findUsers(userIds).stream()
        .collect(Collectors.toMap(
            User::getId,
            user -> new UserProfileResponse(user.getEmail(), user.getNickname())
        ));
  }
}
