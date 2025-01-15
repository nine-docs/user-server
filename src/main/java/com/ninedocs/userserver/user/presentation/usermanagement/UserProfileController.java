package com.ninedocs.userserver.user.presentation.usermanagement;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.userprofile.UserProfileService;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService userProfileService;
  private final UserRepository userRepository;

  @GetMapping("/api/v1/user/{userId}")
  public ResponseEntity<ApiResponse<UserProfileResponse>> checkUser(
      @PathVariable Long userId
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(
        userProfileService.getUserProfile(userId)
    ));
  }
}
