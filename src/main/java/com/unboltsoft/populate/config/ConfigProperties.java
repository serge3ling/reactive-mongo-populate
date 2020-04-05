package com.unboltsoft.populate.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "unboltsoft")
public class ConfigProperties {
  List<String> allowedOrigins;
  String fakeTitle;

  public List<String> getAllowedOrigins() {
    return allowedOrigins;
  }

  public void setAllowedOrigins(List<String> allowedOrigins) {
    this.allowedOrigins = allowedOrigins;
  }

  public String getFakeTitle() {
    return fakeTitle;
  }

  public void setFakeTitle(String fakeTitle) {
    this.fakeTitle = fakeTitle;
  }
}
