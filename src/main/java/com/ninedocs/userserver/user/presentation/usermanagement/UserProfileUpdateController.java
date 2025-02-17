package com.ninedocs.userserver.user.presentation.usermanagement;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.userprofile.UserProfileUpdateService;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.presentation.usermanagement.dto.UserNicknameUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 관리")
@RestController
@RequiredArgsConstructor
public class UserProfileUpdateController {

  private final UserProfileUpdateService userProfileUpdateService;

  @Operation(summary = "닉네임 변경")
  @PutMapping("/api/v1/user/{userId}/nickname")
  public ResponseEntity<ApiResponse<UserProfileResponse>> updateNickname(
      @PathVariable Long userId,
      @RequestBody @Valid UserNicknameUpdateRequest request
  ) {
    UserProfileResponse response = userProfileUpdateService.updateNickname(
        userId, request.getNewNickname()
    );
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}
