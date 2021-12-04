package com.github.kurtloong.acreathstarterespool.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;

public class RestClientPoolConfig extends GenericObjectPoolConfig {

    public RestClientPoolConfig() {
        setTestWhileIdle(true);
        setMinEvictableIdleTime(Duration.ofMillis(6000));
        setTimeBetweenEvictionRuns(Duration.ofMillis(3000));
        setNumTestsPerEvictionRun(-1);
    }
}

