package com.ninedocs.userserver.presentation;

import com.ninedocs.userserver.application.emailverficationcode.EmailVerificationCodeService;
import com.ninedocs.userserver.application.emailverficationcode.dto.EmailDuplicateCodeRequest;
import com.ninedocs.userserver.application.emailverficationcode.dto.EmailVerificationCodeResponse;
import com.ninedocs.userserver.application.response.ApiResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailVerficationController {

  private final EmailVerificationCodeService emailVerificationCodeService;

  @PostMapping("/api/v1/user/email-verfication-code")
  public ApiResponse<EmailVerificationCodeResponse> sendVerificationCode(
      @RequestBody EmailDuplicateCodeRequest emailDuplicateCodeRequest) {
    // redis 저장 시간 가져오기
    long startTime = emailVerificationCodeService.sendVerificationCode(emailDuplicateCodeRequest);
    //exfiredat 시간 설정 (startTime + 5분)
    LocalDateTime startDateTime = LocalDateTime.ofEpochSecond(startTime / 1000, 0,
        java.time.ZoneOffset.UTC);
    LocalDateTime expiryDateTime = startDateTime.plusMinutes(5);
    String formattedTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
        .format(expiryDateTime);
    EmailVerificationCodeResponse emailVerificationCodeResponse = new EmailVerificationCodeResponse();
    emailVerificationCodeResponse.setVerficationCodeExpiredAt(formattedTime);
    return ApiResponse.success(emailVerificationCodeResponse);
  }

}
