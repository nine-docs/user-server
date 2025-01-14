package com.ninedocs.userserver.user.presentation;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.userprofile.UserProfileService;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileRequest;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService userProfileService;
  private final UserRepository userRepository;

  @PostMapping("/api/v1/user/check")
  public ResponseEntity<ApiResponse<UserProfileResponse>> checkUser(
      UserProfileRequest userProfileRequest) {
    UserProfileResponse userProfileResponse = userProfileService.getUserProfile(
        userProfileRequest.getId());
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userProfileResponse));
  }
}
