package com.ninedocs.userserver.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(EmailWebClientProperties.class)
@RequiredArgsConstructor
public class WebClientConfig {

  private final WebClient.Builder webClient;

  @Bean
  public WebClient webClient(EmailWebClientProperties properties) {
    return webClient
        .baseUrl(properties.getUrl())
        .defaultHeader("Content-Type", "application/json")
        .build();
  }
}