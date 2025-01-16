package com.ninedocs.userserver.user.application.emailverificationcode;

import com.ninedocs.userserver.common.util.DateTimeUtil;
import com.ninedocs.userserver.user.application.emailverificationcode.dto.EmailDuplicateCodeRequest;
import com.ninedocs.userserver.user.application.emailverificationcode.dto.EmailSenderRequest;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.DuplicateEmailException;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.EmailFormatException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailVerificationCodeService {

  private final EmailVerificationCodeGenerator emailVerificationCodeGenerator;
  private final EmailDuplicationChecker emailDuplicationChecker;
  private final EmailVerificationCodeRepository emailVerificationCodeRepository;
  private final EmailSender emailSender;

  public LocalDateTime sendVerificationCode(EmailDuplicateCodeRequest emailDuplicateCodeRequest) {
    String email = emailDuplicateCodeRequest.getEmail();

    validateEmail(email);

    checkDuplicateEmail(email);

    String code = emailVerificationCodeGenerator.generateVerificationCode();

    HttpStatusCode httpStatusCode = emailSender.sendEmail(new EmailSenderRequest(email, code));

    if (httpStatusCode != HttpStatus.CREATED) {
      log.error(httpStatusCode.toString());
    }

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
