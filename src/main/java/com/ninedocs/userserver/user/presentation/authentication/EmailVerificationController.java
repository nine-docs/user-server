package com.ninedocs.userserver.user.presentation.authentication;


import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.emailverification.EmailVerificationService;
import com.ninedocs.userserver.user.application.emailverification.dto.EmailVerificationRequest;
import com.ninedocs.userserver.user.application.emailverification.dto.EmailVerificationResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailVerificationController {

  private final EmailVerificationService emailVerificationService;

  @PostMapping("/api/v1/user/email-verification")
  public ApiResponse<EmailVerificationResponse> verificationCode(
      @Valid @RequestBody EmailVerificationRequest emailVerificationRequest) {
    LocalDateTime expiredAt = emailVerificationService.emailVerification(emailVerificationRequest);
    EmailVerificationResponse response = new EmailVerificationResponse(expiredAt);
    return ApiResponse.success(response);
  }

}
