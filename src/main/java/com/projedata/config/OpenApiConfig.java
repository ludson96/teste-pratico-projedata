package com.projedata.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Gestão de Funcionários - Projedata")
                .version("v1.0.0")
                .description("API RESTful para gerenciamento de funcionários, cálculo de reajustes, análises salariais e relatórios estatísticos.")
                .contact(new Contact()
                    .name("Ludson")
                    .url("https://github.com/ludson96/teste-pratico-projedata"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")));
    }
}
