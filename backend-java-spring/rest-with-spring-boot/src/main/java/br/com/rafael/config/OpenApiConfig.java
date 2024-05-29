package br.com.rafael.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
  //http://localhost:8080/swagger-ui/index.html swagger access
  
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("RESTful API with Java 21 and Spring Boot 3")
        .version("v1")
        .description("")
        .termsOfService("")
        .license(new License().name("Apache 2.0")));
  }
}
