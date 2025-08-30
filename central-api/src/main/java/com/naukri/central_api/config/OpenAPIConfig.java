package com.naukri.central_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI centralApiDocumentation() {
        return new OpenAPI()
                .info(new Info()
                        .title("Naukri.com Clone - Central API")
                        .description("API documentation for the Central API Gateway of Naukri.com Clone")
                        .version("1.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
