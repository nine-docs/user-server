package com.ninedocs.userserver.application.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

  @Schema
  @NotNull
  private String email;
  private String password;
}
