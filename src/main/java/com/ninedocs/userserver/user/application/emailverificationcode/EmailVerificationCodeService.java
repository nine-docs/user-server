package com.ninedocs.userserver.user.application.emailverificationcode;

import com.ninedocs.userserver.common.util.DateTimeUtil;
import com.ninedocs.userserver.user.application.emailverificationcode.dto.EmailDuplicateCodeRequest;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.DuplicateEmailException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationCodeService {

  private final EmailVerificationCodeGenerator emailVerificationCodeGenerator;
  private final EmailDuplicationChecker emailDuplicationChecker;
  //private final EmailNotificationService emailNotificationService;
  private final EmailVerificationCodeRepository emailVerificationCodeRepository;

  public LocalDateTime sendVerificationCode(EmailDuplicateCodeRequest emailDuplicateCodeRequest) {
    String email = emailDuplicateCodeRequest.getEmail();

    validateEmail(email);

    checkDuplicateEmail(email);

    String code = emailVerificationCodeGenerator.generateVerificationCode();
    long expire = emailVerificationCodeRepository.saveVerificationCode(email, code);
    // redis 저장 시각을 리턴
    return DateTimeUtil.convertLongToLocalDateTime(expire);
  }

  private void validateEmail(String email) {
    if (!EmailFormatValidator.isValid(email)) {
      throw new IllegalArgumentException("Invalid email");
    }
  }

  private void checkDuplicateEmail(String email) {
    if (emailDuplicationChecker.isDuplicateEmail(email)) {
      throw new DuplicateEmailException();
    }
  }
}
