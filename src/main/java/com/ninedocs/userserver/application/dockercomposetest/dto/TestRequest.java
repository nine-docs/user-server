package com.ninedocs.userserver.application.dockercomposetest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TestRequest {

  @NotNull
  @Schema(example = "example@example.com")
  private String email;

  @NotNull
  @Schema(example = "example-password")
  private String password;

}
