package com.ninedocs.userserver.user.application.signin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignInRequest {

  @NotNull
  @Schema(example = "example-email")
  private String email;

  @NotNull
  @Schema(example = "example-password")
  private String password;

}
