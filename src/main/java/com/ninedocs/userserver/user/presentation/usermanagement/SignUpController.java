package com.ninedocs.userserver.user.presentation.usermanagement;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.signin.JwtProvider;
import com.ninedocs.userserver.user.application.signin.dto.JwtTokenResult;
import com.ninedocs.userserver.user.application.signup.SignUpService;
import com.ninedocs.userserver.user.application.signup.dto.SignUpRequest;
import com.ninedocs.userserver.user.application.signup.dto.SignUpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

  private final SignUpService signUpService;
  private final JwtProvider jwtProvider;

  @PostMapping("/api/v1/user")
  public ResponseEntity<ApiResponse<SignUpResponse>> signUp(
      @Valid @RequestBody SignUpRequest signUpRequest) {
    long id = signUpService.signUp(signUpRequest.getEmail(), signUpRequest.getPassword(),
        signUpRequest.getNickname());
    JwtTokenResult token = jwtProvider.generateAccessToken(id);
    SignUpResponse signUpResponse = SignUpResponse.builder()
        .accessTokenExpiredAt(token.getExpiredAt())
        .id(id)
        .token(token.getToken())
        .build();
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(signUpResponse));
  }


}
