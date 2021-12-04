package com.github.kurtloong.acreathstarterespool.exception;

public class ElasticsearchAccessException extends RuntimeException {

    public ElasticsearchAccessException(String msg) {
        super(msg);
    }

    public ElasticsearchAccessException(Throwable cause) {
        super(cause);
    }

    public ElasticsearchAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
