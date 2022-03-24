package com.itana.carsregistrationapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "CarRegistrationOpenApi")
    public OpenAPI CarsRegistrationOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cars Registration Application API")
                        .description("Cars Registration API implemented with Spring Boot RESTful service and " +
                                "documented using springdoc-openapi")
                );
    }
}
