package com.technologyos.task.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "task")
@Data
public class ConfigProperties {

    private String msg;
    private String version;
    private Map<String, String> details;
}
