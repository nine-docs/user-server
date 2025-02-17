package com.ninedocs.userserver.user.application.userprofile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserPasswordUpdateRequest {

  @NotEmpty
  private String originalPassword;

  @NotNull
  @Schema(example = "new-password")
  @Size(min = 8, max = 50)
  private String newPassword;
}
