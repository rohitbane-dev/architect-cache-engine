package com.rohit.project.policy.write;

import com.rohit.project.storage.interfaces.CacheStorage;
import com.rohit.project.storage.interfaces.DBStorage;

public interface WritePolicy<K, V> {
    void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception;

}
