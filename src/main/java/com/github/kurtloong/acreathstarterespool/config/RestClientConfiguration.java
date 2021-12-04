package com.github.kurtloong.acreathstarterespool.config;

import lombok.Data;
import org.apache.http.HttpHost;

@Data
public abstract class RestClientConfiguration {
    private int connectTimeout = -1;
    private int connectionRequestTimeout = -1;
    private int socketTimeout = -1;
    private int maxConnTotal = 0;
    private int maxConnPerRoute = 0;

    public abstract HttpHost[] getHttpHosts();


}
