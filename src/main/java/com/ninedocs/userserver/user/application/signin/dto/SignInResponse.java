package com.ninedocs.userserver.user.application.signin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {

  @Schema(example = "example-token")
  private String token;

  @Schema(example = "2025-01-09 14:52:15.672743", type = "string")
  private LocalDateTime accessTokenExpiredAt;
}
