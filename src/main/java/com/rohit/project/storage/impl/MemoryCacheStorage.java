package com.rohit.project.storage.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.rohit.project.storage.interfaces.CacheStorage;

public class MemoryCacheStorage<K,V> implements CacheStorage<K, V>{
    private final int capacity;
    private final Map<K, V> cache;

    MemoryCacheStorage(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();    
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) throws Exception {
        if(!cache.containsKey(key)) {
            throw new Exception("Key not found in cache: " + key);
        }
        return cache.get(key);
    }

    @Override
    public void remove(K key) throws Exception {
        if(!cache.containsKey(key)) {
            throw new Exception("Key not found in cache: " + key);
        }
        cache.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public int capacity() { 
        return capacity;
    }   
}
