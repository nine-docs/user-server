package com.ninedocs.userserver.user.presentation;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.emailverificationcode.EmailFormatValidator;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.EmailFormatException;
import com.ninedocs.userserver.user.application.signup.EmailVerificationChecker;
import com.ninedocs.userserver.user.application.signup.SignUpService;
import com.ninedocs.userserver.user.application.signup.dto.SignUpRequest;
import com.ninedocs.userserver.user.application.signup.dto.SignUpResponse;
import com.ninedocs.userserver.user.application.signup.exception.EmailNotVerifiedException;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

  private final EmailVerificationChecker emailVerificationChecker;
  private final SignUpService signUpService;
  private final UserRepository userRepository;

  @PostMapping("/api/v1/user")
  public ApiResponse<SignUpResponse> signUp(SignUpRequest signUpRequest) {
    if (!EmailFormatValidator.isValid(signUpRequest.getEmail())) {
      throw new EmailFormatException();
    }
    if (!emailVerificationChecker.checkEmailVerification(signUpRequest)) {
      throw new EmailNotVerifiedException();
    }
    String password = signUpService.getHashedPassword(signUpRequest.getPassword());
    User user = User.builder().email(signUpRequest.getEmail()).password(password)
        .nickname(signUpRequest.getNickname()).build();
    long id = userRepository.save(user).getId();
    SignUpResponse signUpResponse = new SignUpResponse(id, "example-token", LocalDateTime.now());
    return ApiResponse.success(signUpResponse);
  }


}
