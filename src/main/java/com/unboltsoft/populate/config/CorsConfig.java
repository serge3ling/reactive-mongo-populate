package com.unboltsoft.populate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsConfig implements WebFluxConfigurer {

  @NonNull private final ConfigProperties configProperties;

  public CorsConfig(ConfigProperties configProperties) {
    this.configProperties = configProperties;
  }

  @NonNull
  @Bean
  CorsWebFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    configProperties.getAllowedOrigins().forEach(config::addAllowedOrigin);
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return new CorsWebFilter(source);
  }
}
