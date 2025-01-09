package com.ninedocs.userserver.user.application.emailverificationcode;

import com.ninedocs.userserver.common.util.DateTimeUtil;
import com.ninedocs.userserver.user.application.emailverificationcode.dto.EmailDuplicateCodeRequest;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.DuplicateEmailException;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.EmailFormatException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationCodeService {

  private final EmailVerificationCodeGenerator emailVerificationCodeGenerator;
  private final EmailDuplicationChecker emailDuplicationChecker;
  private final EmailVerificationCodeRepository emailVerificationCodeRepository;

  public LocalDateTime sendVerificationCode(EmailDuplicateCodeRequest emailDuplicateCodeRequest) {
    String email = emailDuplicateCodeRequest.getEmail();

    validateEmail(email);

    checkDuplicateEmail(email);

    String code = emailVerificationCodeGenerator.generateVerificationCode();
    // redis 저장 시각을 리턴
    return emailVerificationCodeRepository.saveVerificationCode(email, code);
  }

  private void validateEmail(String email) {
    if (!EmailFormatValidator.isValid(email)) {
      throw new EmailFormatException();
    }
  }

  private void checkDuplicateEmail(String email) {
    if (emailDuplicationChecker.isDuplicateEmail(email)) {
      throw new DuplicateEmailException();
    }
  }
}
