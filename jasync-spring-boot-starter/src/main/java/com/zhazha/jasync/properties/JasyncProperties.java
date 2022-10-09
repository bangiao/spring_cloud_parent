package com.zhazha.jasync.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.zhazha.jasync")
public class JasyncProperties {
    private boolean enabled = false;
}
