package com.github.kurtloong.acreathstarterespool.core;

import com.github.kurtloong.acreathstarterespool.config.RestClientConfiguration;
import com.github.kurtloong.acreathstarterespool.config.RestClientPoolConfig;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.Map;

public class ElasticsearchClientFactory {

    private RestClientPool<RestHighLevelClient> pool;
    private Map<String, String> defaultHeaders;

    public ElasticsearchClientFactory(RestClientConfiguration configuration, RestClientPoolConfig poolConfig) {
        createPool(configuration, poolConfig);
    }

    public void setDefaultHeaders(Map<String, String> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
    }

    public RestHighLevelClient getResource() {
        return pool.getResource();
    }

    public void returnResource(RestHighLevelClient restHighLevelClient){
        pool.returnResource(restHighLevelClient);
    }

    public void close(){
        if(pool != null){
            pool.close();
        }
    }

    private void createPool(RestClientConfiguration configuration, RestClientPoolConfig poolConfig){
        RestHighLevelClientFactory clientFactory = new RestHighLevelClientFactory(configuration, defaultHeaders);
        pool = new RestClientPool<>(poolConfig, clientFactory);
    }
}
