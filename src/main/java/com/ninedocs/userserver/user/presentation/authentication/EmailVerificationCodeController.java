package com.ninedocs.userserver.user.presentation.authentication;

import com.ninedocs.userserver.user.application.emailverificationcode.EmailVerificationCodeService;
import com.ninedocs.userserver.user.application.emailverificationcode.dto.EmailDuplicateCodeRequest;
import com.ninedocs.userserver.user.application.emailverificationcode.dto.EmailVerificationCodeResponse;
import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
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
public class EmailVerificationCodeController {

  private final EmailVerificationCodeService emailVerificationCodeService;

  @Operation(summary = "이메일 인증 코드 전송")
  @PostMapping("/api/v1/user/email-verification-code")
  public ApiResponse<EmailVerificationCodeResponse> sendVerificationCode(
      @Valid @RequestBody EmailDuplicateCodeRequest emailDuplicateCodeRequest) {
    // redis 저장 시간 가져오기
    LocalDateTime expiredAt = emailVerificationCodeService.sendVerificationCode(
        emailDuplicateCodeRequest);
    EmailVerificationCodeResponse emailVerificationCodeResponse = new EmailVerificationCodeResponse(
        expiredAt);
    return ApiResponse.success(emailVerificationCodeResponse);
  }

}
