package com.gustavo.helpdeskapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI helpdeskOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HelpDesk API")
                        .description("API REST para gerenciamento de usuários, categorias e tickets.")
                        .version("2.0.0")
                );
    }
}