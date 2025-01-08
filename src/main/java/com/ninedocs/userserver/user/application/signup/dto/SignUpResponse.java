package com.ninedocs.userserver.user.application.signup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponse {

  @Schema(example = "1")
  private long id;

  @Schema(example = "example-token")
  private String token;

  @Schema(example = "2024-12-27 19:51:25.125")
  private LocalDateTime accessTokenExpiredAt;
}
