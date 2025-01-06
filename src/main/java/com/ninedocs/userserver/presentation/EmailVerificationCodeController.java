package com.ninedocs.userserver.presentation;

import com.ninedocs.userserver.application.emailverificationcode.EmailVerificationCodeService;
import com.ninedocs.userserver.application.emailverificationcode.dto.EmailDuplicateCodeRequest;
import com.ninedocs.userserver.application.emailverificationcode.dto.EmailVerificationCodeResponse;
import com.ninedocs.userserver.common.response.ApiResponse;
import com.ninedocs.userserver.common.staticmethod.CalculateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailVerificationCodeController {

  private final EmailVerificationCodeService emailVerificationCodeService;

  @PostMapping("/api/v1/user/email-verfication-code")
  public ApiResponse<EmailVerificationCodeResponse> sendVerificationCode(
      @RequestBody EmailDuplicateCodeRequest emailDuplicateCodeRequest) {
    // redis 저장 시간 가져오기
    long startTime = emailVerificationCodeService.sendVerificationCode(emailDuplicateCodeRequest);
    //exfiredat 시간 설정 (startTime + 5분)
    String formattedTime = CalculateTime.MakeExpiredAt(startTime, 5);
    EmailVerificationCodeResponse emailVerificationCodeResponse = new EmailVerificationCodeResponse();
    emailVerificationCodeResponse.setVerificationCodeExpiredAt(formattedTime);
    return ApiResponse.success(emailVerificationCodeResponse);
  }

}
