package com.ninedocs.userserver.user.presentation.authentication;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.signin.JwtProvider;
import com.ninedocs.userserver.user.application.signin.SignInService;
import com.ninedocs.userserver.user.application.signin.dto.JwtTokenResult;
import com.ninedocs.userserver.user.application.signin.dto.SignInRequest;
import com.ninedocs.userserver.user.application.signin.dto.SignInResponse;
import jakarta.validation.Valid;
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
    JwtTokenResult token = jwtProvider.generateAccessToken(uid);
    SignInResponse signInResponse = SignInResponse.builder()
        .accessTokenExpiredAt(token.getExpiredAt())
        .token(token.getToken())
        .build();
    return ApiResponse.success(signInResponse);
  }

}
