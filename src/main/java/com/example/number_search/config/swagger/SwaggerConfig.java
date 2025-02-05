package com.example.number_search.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger (OpenAPI) documentation.
 * <p>
 * This class provides a custom OpenAPI configuration for generating
 * interactive API documentation using Swagger UI.
 * </p>
 *
 * @author [Alexey Abramov]
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates a custom OpenAPI configuration for Swagger UI.
     * <p>
     * This method defines metadata such as API title, version,
     * and description, which will be displayed in Swagger UI.
     * </p>
     *
     * @return an instance of {@link OpenAPI} with custom API metadata
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service API")
                        .version("1.0")
                        .description("API documentation for managing orders"));
    }
}
