package com.ninedocs.userserver.application.emailverificationcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmailVerificationCodeResponse {

  private String verificationCodeExpiredAt;
}
