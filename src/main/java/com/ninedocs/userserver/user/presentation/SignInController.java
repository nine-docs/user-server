package com.ninedocs.userserver.user.presentation;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.signin.JwtProvider;
import com.ninedocs.userserver.user.application.signin.SignInService;
import com.ninedocs.userserver.user.application.signin.dto.SignInRequest;
import com.ninedocs.userserver.user.application.signin.dto.SignInResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignInController {

  private final SignInService signInService;
  private final JwtProvider jwtProvider;

  @PostMapping("api/v1/user/login")
  public ApiResponse<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
    long uid = signInService.signIn(signInRequest.getEmail(), signInRequest.getPassword());
    Date now = jwtProvider.generateNow();
    Date expiryDate = jwtProvider.generateExpiryDate(now);
    String token = jwtProvider.generateAccessToken(uid, expiryDate, now);
    LocalDateTime accessTokenExpiredAt = jwtProvider.convertToLocalDateTime(expiryDate);
    return ApiResponse.success(new SignInResponse(token, accessTokenExpiredAt));
  }

}
