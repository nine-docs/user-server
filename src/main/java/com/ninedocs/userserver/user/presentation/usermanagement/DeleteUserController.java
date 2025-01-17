package com.ninedocs.userserver.user.presentation.usermanagement;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.deleteuser.DeleteUserService;
import com.ninedocs.userserver.user.application.deleteuser.dto.DeleteUserRequest;
import com.ninedocs.userserver.user.application.deleteuser.dto.DeleteUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 관리")
@RestController
@RequiredArgsConstructor
public class DeleteUserController {

  private final DeleteUserService deleteUserService;

  @Operation(summary = "회원 탈퇴")
  @DeleteMapping("api/v1/user/{userId}")
  public ApiResponse<DeleteUserResponse> deleteUser(@PathVariable Long userId) {
    deleteUserService.deleteUser(userId);
    DeleteUserResponse response = new DeleteUserResponse("success");
    return ApiResponse.success(response);
  }
}
