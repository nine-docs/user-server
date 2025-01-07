package com.ninedocs.userserver.common.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping("/api/v1/system/health")
  public String health() {
    return "Healthy";
  }
}
