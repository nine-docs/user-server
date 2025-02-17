package com.ninedocs.userserver.user.presentation.usermanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserNicknameUpdateRequest {

  @NotNull
  @Schema(example = "example-nickname")
  @Size(min = 1, max = 50)
  private String newNickname;
}
