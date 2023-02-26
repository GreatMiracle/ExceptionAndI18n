package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Kiên", version = "2.0", description = "Kien test swagger"))
public class SwaggerConfig {
    private final static Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
}
