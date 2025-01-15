package com.ninedocs.userserver.user.presentation.usermanagement;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.userprofile.UserProfileService;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 관리")
@RestController
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService userProfileService;

  @Operation(summary = "유저 정보 조회")
  @GetMapping("/api/v1/user/{userId}")
  public ResponseEntity<ApiResponse<UserProfileResponse>> checkUser(
      @PathVariable("userId") long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(
        userProfileService.getUserProfile(userId)
    ));
  }
}
