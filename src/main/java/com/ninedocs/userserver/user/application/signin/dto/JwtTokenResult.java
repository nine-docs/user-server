package com.ninedocs.userserver.user.application.signin.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtTokenResult {

  private LocalDateTime expiredAt;
  private String token;
}
