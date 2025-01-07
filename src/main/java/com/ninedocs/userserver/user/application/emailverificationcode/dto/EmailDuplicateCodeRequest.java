package com.ninedocs.userserver.user.application.emailverificationcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailDuplicateCodeRequest {

  @NotNull
  @Size(max = 255, min = 1)
  @Schema(example = "example@example.com")
  private String email;
}
