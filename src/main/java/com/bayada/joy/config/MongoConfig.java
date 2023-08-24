package com.bayada.joy.config;


import com.bayada.joy.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@Configuration
@EnableMongoAuditing(auditorAwareRef = "auditorProvider")

public class MongoConfig {

  @Bean
  AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }

}
