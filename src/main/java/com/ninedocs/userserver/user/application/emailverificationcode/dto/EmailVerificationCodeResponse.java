package com.ninedocs.userserver.user.application.emailverificationcode.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class EmailVerificationCodeResponse {

  private LocalDateTime verificationCodeExpiredAt;
}
