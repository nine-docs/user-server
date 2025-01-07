package com.ninedocs.userserver.user.application.emailverification;

import com.ninedocs.userserver.user.application.emailverification.dto.EmailVerificationRequest;
import com.ninedocs.userserver.user.application.emailverification.exception.EmailVerificationException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

  private final RedisService redisService;
  //redis 만료기간 설정
  private static final long VERIFIED_EXPIRATION = 30;

  public LocalDateTime emailVerification(EmailVerificationRequest emailVerificationRequest) {
    String email = emailVerificationRequest.getEmail();
    String emailVerificationCode = emailVerificationRequest.getEmailVerificationCode();

    // 인증 조회
    String storedCode = redisService.getVerificationCode(email);
    if (storedCode == null || !storedCode.equals(emailVerificationCode)) {
      throw new EmailVerificationException();
    }

    // 인증 성공 처리
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime expiredAt = now.plusMinutes(VERIFIED_EXPIRATION);
    redisService.storeVerification(email, VERIFIED_EXPIRATION);

    // 기존 redis 저장 코드 삭제
    redisService.deleteVerificationCode(email);

    return expiredAt;
  }
}
