package com.ninedocs.userserver.application.emailverificationcode;

import com.ninedocs.userserver.application.emailverificationcode.dto.EmailDuplicateCodeRequest;
import com.ninedocs.userserver.application.emailverificationcode.exception.DuplicateEmailException;
import com.ninedocs.userserver.application.emailverificationcode.exception.EmailFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationCodeService {

  private final EmailVerificationCode emailVerificationCode;
  private final EmailIsDuplicate emailIsDuplicate;

  public long sendVerificationCode(EmailDuplicateCodeRequest emailDuplicateCodeRequest) {
    String email = emailDuplicateCodeRequest.getEmail();

    validateEmail(email);

    checkDuplicateEmail(email);

    String code = emailVerificationCode.generateVerificationCode();
    return emailVerificationCode.saveVerificationCode(email, code);
    // redis 저장 시각을 리턴
  }

  private void validateEmail(String email) {
    if (!EmailValidator.isValid(email)) {
      throw new EmailFormatException();
    }
  }

  private void checkDuplicateEmail(String email) {
    if (emailIsDuplicate.isDuplicateEmail(email)) {
      throw new DuplicateEmailException();
    }
  }
}
