package com.ninedocs.userserver.user.application.emailverificationcode.dto;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class EmailVerificationCodeResponse {

  private LocalDateTime verificationCodeExpiredAt;
}
