package com.rohit.project.policy.write.impl;

import java.util.concurrent.CompletableFuture;

import com.rohit.project.policy.write.WritePolicy;
import com.rohit.project.storage.interfaces.CacheStorage;
import com.rohit.project.storage.interfaces.DBStorage;

public class WriteThroughPolicy<K, V> implements WritePolicy<K, V> {
    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception {
        CompletableFuture.runAsync(() -> {
            try {
                cacheStorage.put(key, value);
            } catch (Exception e) {
                // Handle exception (e.g., log it)
                e.printStackTrace();
            }
        });

        CompletableFuture.runAsync(() -> {
            try {
                dbStorage.save(key, value);
            } catch (Exception e) {
                // Handle exception (e.g., log it)
                e.printStackTrace();
            }
        });
    }

}
