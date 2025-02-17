package com.ninedocs.userserver.user.application.signup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {

  @NotNull
  @Schema(example = "example@example.com")
  @Size(min = 1, max = 255)
  private String email;

  @NotNull
  @Schema(example = "example-nickname")
  @Size(min = 1, max = 50)
  private String nickname;

  @NotNull
  @Schema(example = "example-password")
  @Size(min = 8, max = 50)
  private String password;
}
