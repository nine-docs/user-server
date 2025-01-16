package com.ninedocs.userserver.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient webClient(WebClient.Builder builder) {
    return builder
        .baseUrl("http://15.165.213.49:3000")
        .defaultHeader("Content-Type", "application/json")
        .build();
  }
}