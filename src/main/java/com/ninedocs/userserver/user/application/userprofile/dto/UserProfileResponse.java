package com.ninedocs.userserver.user.application.userprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponse {

  private String email;
  private String nickname;
}
