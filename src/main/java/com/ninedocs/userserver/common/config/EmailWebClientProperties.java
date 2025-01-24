package com.ninedocs.userserver.common.config;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "domain.mail")
@Getter
@RequiredArgsConstructor
public class EmailWebClientProperties {

  private final String url;
}
