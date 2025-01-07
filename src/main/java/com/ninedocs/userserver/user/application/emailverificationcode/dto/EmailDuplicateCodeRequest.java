package com.ninedocs.userserver.user.application.emailverificationcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailDuplicateCodeRequest {

  @NotNull
  @Schema(example = "example@example.com")
  private String email;
}
