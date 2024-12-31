package com.ninedocs.userserver.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 설정
 */

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("User Server API")
            .version("1.0"));
  }

  @Bean
  public GroupedOpenApi systemGroupedOpenApi() {
    return GroupedOpenApi.builder()
        .group("(z) System")
        .pathsToMatch("/api/v1/system/**")
        .build();
  }
}
