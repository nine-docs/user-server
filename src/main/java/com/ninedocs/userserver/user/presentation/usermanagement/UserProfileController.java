package com.ninedocs.userserver.user.presentation.usermanagement;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.userprofile.UserProfileBulkQueryService;
import com.ninedocs.userserver.user.application.userprofile.UserProfileQueryService;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.presentation.usermanagement.dto.UserBulkQueryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 관리")
@RestController
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileQueryService userProfileQueryService;
  private final UserProfileBulkQueryService userProfileBulkQueryService;

  @Operation(summary = "유저 정보 조회")
  @GetMapping("/api/v1/user/{userId}")
  public ResponseEntity<ApiResponse<UserProfileResponse>> getUser(
      @PathVariable("userId") Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(
        userProfileQueryService.getUserProfile(userId)
    ));
  }

  @Operation(summary = "유저 정보 bulk 조회")
  @PostMapping("/api/v1/user/bulk-get")
  public ResponseEntity<ApiResponse<Map<Long, UserProfileResponse>>> getUsers(
      @RequestBody UserBulkQueryRequest request
  ) {
    return ResponseEntity.ok(ApiResponse.success(
        userProfileBulkQueryService.getUserProfiles(request.getUserIds())
    ));
  }
}
