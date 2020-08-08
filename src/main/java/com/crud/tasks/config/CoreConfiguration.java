package com.crud.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfiguration {
    @Bean                              /**  <-- czy to fabryka bean'ów? Do zapytania na spotkaniu */
    public RestTemplate restTemplate() {
      return  new RestTemplate();
    }
}
