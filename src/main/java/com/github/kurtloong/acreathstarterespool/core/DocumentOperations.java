package com.github.kurtloong.acreathstarterespool.core;

public interface DocumentOperations {
    <T> T execute(RestClientCallback<T> callback);

    void execute(RestClientAsyncCallback callback);
}
