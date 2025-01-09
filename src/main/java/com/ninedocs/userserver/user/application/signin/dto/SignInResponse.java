package com.ninedocs.userserver.user.application.signin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SignInResponse {

  @NotNull
  @Schema(example = "example-token")
  private String token;

  @NotNull
  @Schema(example = "accessTokenExpiredAt")
  private LocalDateTime accessTokenExpiredAt;
}
