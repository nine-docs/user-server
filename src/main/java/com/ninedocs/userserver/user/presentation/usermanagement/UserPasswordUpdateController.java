package com.ninedocs.userserver.user.presentation.usermanagement;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.userprofile.UserPasswordUpdateService;
import com.ninedocs.userserver.user.application.userprofile.dto.UserPasswordUpdateRequest;
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
public class UserPasswordUpdateController {

  private final UserPasswordUpdateService userPasswordUpdateService;

  @Operation(summary = "비밀번호 변경")
  @PutMapping("/api/v1/user/{userId}/password")
  public ResponseEntity<ApiResponse<Void>> updatePassword(
      @PathVariable Long userId,
      @RequestBody @Valid UserPasswordUpdateRequest request
  ) {
    userPasswordUpdateService.updatePassword(userId, request);
    return ResponseEntity.ok(ApiResponse.empty());
  }
}
