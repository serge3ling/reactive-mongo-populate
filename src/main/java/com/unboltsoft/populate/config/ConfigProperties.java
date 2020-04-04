package com.unboltsoft.populate.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "unboltsoft")
public class ConfigProperties {
  List<String> allowedOrigins;
  String fakeDescription;

  public List<String> getAllowedOrigins() {
    return allowedOrigins;
  }

  public void setAllowedOrigins(List<String> allowedOrigins) {
    this.allowedOrigins = allowedOrigins;
  }

  public String getFakeDescription() {
    return fakeDescription;
  }

  public void setFakeDescription(String fakeDescription) {
    this.fakeDescription = fakeDescription;
  }
}
