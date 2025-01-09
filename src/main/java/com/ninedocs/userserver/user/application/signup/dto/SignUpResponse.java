package com.ninedocs.userserver.user.application.signup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponse {

  @Schema(example = "1")
  private long id;

  @Schema(example = "example-token")
  private String token;

  @Schema(example = "2025-01-09 14:52:15.672743", type = "string")
  private LocalDateTime accessTokenExpiredAt;
}
