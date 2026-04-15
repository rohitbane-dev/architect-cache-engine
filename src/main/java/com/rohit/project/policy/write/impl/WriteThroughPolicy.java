package com.rohit.project.policy.write.impl;

import java.util.concurrent.CompletableFuture;

import com.rohit.project.cache.CacheStore;
import com.rohit.project.policy.write.WritePolicy;
import com.rohit.project.storage.interfaces.DBStorage;

public class WriteThroughPolicy<K, V> implements WritePolicy<K, V> {
    public CacheStore<K, V> cacheStore;
    public DBStorage<K, V> dbStorage;

    public WriteThroughPolicy(CacheStore<K, V> store, DBStorage<K, V> db) {
        this.cacheStore = store;
        this.dbStorage = db;
    }

    @Override
    public void write(K key, V value) throws Exception {
        CompletableFuture.runAsync(() -> {
            try {
                dbStorage.save(key, value);
            } catch (Exception e) {
                // Handle exception (e.g., log it)
                e.printStackTrace();
            }
        });
        cacheStore.put(key, value);
    }

}
