package com.ninedocs.userserver.user.presentation;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.deleteuser.DeleteUserService;
import com.ninedocs.userserver.user.application.deleteuser.dto.DeleteUserRequest;
import com.ninedocs.userserver.user.application.deleteuser.dto.DeleteUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteUserController {

  private final DeleteUserService deleteUserService;

  @DeleteMapping("api/v1/user/delete")
  public ApiResponse<DeleteUserResponse> deleteUser(DeleteUserRequest request) {
    long id = request.getId();
    deleteUserService.deleteUser(id);
    DeleteUserResponse response = new DeleteUserResponse("success");
    return ApiResponse.success(response);
  }
}
