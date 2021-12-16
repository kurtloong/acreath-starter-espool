package com.github.kurtloong.acreathstarterespool.core;

import com.github.kurtloong.acreathstarterespool.exception.ElasticsearchAccessException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

@Slf4j
public class RestClientTemplate extends RestClientAccessor implements DocumentOperations{
    long slowTime = 5000;

    public RestClientTemplate() {
    }

    public RestClientTemplate(ElasticsearchClientFactory restClientFactory) {
        setConnectionFactory(restClientFactory);
    }


    @Override
    public <T> T execute(RestClientCallback<T> callback) {
        try {
            long start = System.currentTimeMillis();
            ElasticsearchClientFactory clientFactory = getRestClientFactory();
            RestHighLevelClient client = clientFactory.getResource();
            T t = callback.doInRestClient(client);
            clientFactory.returnResource(client);
            long end = System.currentTimeMillis();
            long timeConsuming = end - start;
            if (timeConsuming>=slowTime){
                log.warn("es 慢查询 {} !! " , timeConsuming);
            }
            return t;
        } catch (IOException e) {
            throw new ElasticsearchAccessException(e);
        }
    }

    @Override
    public void execute(RestClientAsyncCallback callback) {
        try {
            RestHighLevelClient client = getRestClientFactory().getResource();
            callback.doInRestAsyncClient(client);
            getRestClientFactory().returnResource(client);
        } catch (IOException e) {
            throw new ElasticsearchAccessException(e);
        }
    }
}
