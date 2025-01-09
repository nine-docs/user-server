package com.ninedocs.userserver.user.application.emailverificationcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class EmailVerificationCodeResponse {
  
  @Schema(example = "2025-01-09 14:52:15.672743", type = "string")
  private LocalDateTime verificationCodeExpiredAt;
}
