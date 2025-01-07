package com.ninedocs.userserver.user.application.emailverification.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailVerificationResponse {

  private LocalDateTime verificationExpiredAt;

}
