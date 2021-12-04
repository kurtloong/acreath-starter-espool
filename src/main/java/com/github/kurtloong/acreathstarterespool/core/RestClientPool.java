package com.github.kurtloong.acreathstarterespool.core;

import com.github.kurtloong.acreathstarterespool.exception.RestClientException;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.Closeable;
import java.util.NoSuchElementException;

public class RestClientPool<T> implements Closeable {

    private GenericObjectPool<T> internalPool;

    public RestClientPool(GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        initPool(poolConfig, factory);
    }

    @Override
    public void close() {
        closeInternalPool();
    }

    public boolean isClosed() {
        return internalPool.isClosed();
    }

    public void initPool(GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        if (internalPool != null) {
            try {
                closeInternalPool();
            } catch (Exception e) {
                throw new RestClientException("init pool error");
            }
        }
        internalPool = new GenericObjectPool<T>(factory, poolConfig);
    }

    public T getResource() {
        try {
            return internalPool.borrowObject();
        } catch (Exception e) {
            throw new RestClientException("Could not get a resource from the pool", e);
        }
    }

    public void returnResource(T resource) {
        if (resource != null) {
            try {
                internalPool.returnObject(resource);
            } catch (Exception e) {
                throw new RestClientException("Could not return the resource to the pool", e);
            }
        }

    }

    private void closeInternalPool() {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new RestClientException("Could not destroy the pool", e);
        }
    }

    public int getNumActive() {
        if (poolInactive()) {
            return -1;
        }

        return this.internalPool.getNumActive();
    }

    public int getNumIdle() {
        if (poolInactive()) {
            return -1;
        }

        return this.internalPool.getNumIdle();
    }

    public int getNumWaiters() {
        if (poolInactive()) {
            return -1;
        }

        return this.internalPool.getNumWaiters();
    }

    public long getMeanBorrowWaitTimeMillis() {
        if (poolInactive()) {
            return -1;
        }
        return this.internalPool.getMeanBorrowWaitTimeMillis();
    }

    public long getMaxBorrowWaitTimeMillis() {
        if (poolInactive()) {
            return -1;
        }

        return this.internalPool.getMaxBorrowWaitTimeMillis();
    }

    private boolean poolInactive() {
        return this.internalPool == null || this.internalPool.isClosed();
    }

    public void addObjects(int count) {
        try {
            for (int i = 0; i < count; i++) {
                this.internalPool.addObject();
            }
        } catch (Exception e) {
            throw new RestClientException("Error trying to add idle objects", e);
        }
    }
}
