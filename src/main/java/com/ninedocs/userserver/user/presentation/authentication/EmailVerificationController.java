package com.ninedocs.userserver.user.presentation.authentication;


import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.emailverification.EmailVerificationService;
import com.ninedocs.userserver.user.application.emailverification.dto.EmailVerificationRequest;
import com.ninedocs.userserver.user.application.emailverification.dto.EmailVerificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증 기능")
@RestController
@RequiredArgsConstructor
public class EmailVerificationController {

  private final EmailVerificationService emailVerificationService;

  @Operation(summary = "이메일 인증")
  @PostMapping("/api/v1/user/email-verification")
  public ApiResponse<EmailVerificationResponse> verificationCode(
      @Valid @RequestBody EmailVerificationRequest emailVerificationRequest) {
    LocalDateTime expiredAt = emailVerificationService.emailVerification(emailVerificationRequest);
    EmailVerificationResponse response = new EmailVerificationResponse(expiredAt);
    return ApiResponse.success(response);
  }

}
