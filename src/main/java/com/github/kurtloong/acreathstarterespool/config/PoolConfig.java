package com.github.kurtloong.acreathstarterespool.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "es-pool")
public class PoolConfig {
}
