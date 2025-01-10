package com.ninedocs.userserver.user.application.deleteuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeleteUserRequest {

  @Schema(example = "1")
  private long id;
}
