package com.technologyos.employee.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "employee")
@Data
public class ConfigProperties {

    private String msg;
    private String version;
    private Map<String, String> details;
}
