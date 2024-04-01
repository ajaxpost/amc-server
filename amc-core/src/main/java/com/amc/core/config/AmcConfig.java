package com.amc.core.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "amc")
@Data
@ToString
public class AmcConfig {
    private String name;

    private String version;

    private String desc;

    private String author;

    private String url;

    private String email;


}
