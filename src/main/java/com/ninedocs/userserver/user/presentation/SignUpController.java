package com.ninedocs.userserver.user.presentation;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.emailverificationcode.EmailFormatValidator;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.EmailFormatException;
import com.ninedocs.userserver.user.application.signin.JwtProvider;
import com.ninedocs.userserver.user.application.signup.EmailVerificationChecker;
import com.ninedocs.userserver.user.application.signup.SignUpService;
import com.ninedocs.userserver.user.application.signup.dto.SignUpRequest;
import com.ninedocs.userserver.user.application.signup.dto.SignUpResponse;
import com.ninedocs.userserver.user.application.signup.exception.EmailNotVerifiedException;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

  private final EmailVerificationChecker emailVerificationChecker;
  private final SignUpService signUpService;
  private final UserRepository userRepository;
  private final JwtProvider jwtProvider;

  @PostMapping("/api/v1/user")
  public ResponseEntity<ApiResponse<SignUpResponse>> signUp(
      @Valid @RequestBody SignUpRequest signUpRequest) {
    long id = signUpService.signUp(signUpRequest.getEmail(), signUpRequest.getPassword(),
        signUpRequest.getNickname());
    Date now = new Date();
    Date expiry = jwtProvider.generateExpiryDate(now);
    //jwt토큰 생성
    String token = jwtProvider.generateAccessToken(id, expiry, now);
    //LocalDateTime으로 타입 변경
    LocalDateTime accessTokenExpiredAt = jwtProvider.convertToLocalDateTime(expiry);
    SignUpResponse signUpResponse = new SignUpResponse(id, token, accessTokenExpiredAt);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(signUpResponse));
  }


}
