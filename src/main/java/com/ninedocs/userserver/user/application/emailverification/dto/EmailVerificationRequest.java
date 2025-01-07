package com.ninedocs.userserver.user.application.emailverification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailVerificationRequest {

  @NotNull
  @Size(max = 255, min = 1)
  @Schema(example = "example1@example.com")
  private String email;
  
  @NotNull
  @Size(min = 6, max = 6)
  @Schema(example = "111111")
  private String emailVerificationCode;
}
