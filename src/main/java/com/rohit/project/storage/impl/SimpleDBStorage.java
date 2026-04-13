package com.rohit.project.storage.impl;

import java.util.concurrent.ConcurrentHashMap;

public class SimpleDBStorage<K,V> implements DBStorage<K, V>{
    private final Map<K, V> database;

    SimpleDBStorage() {
        this.database = new ConcurrentHashMap<>();    
    }

    @Override
    public void save(K key, V value) {
        database.put(key, value);
    }

    @Override
    public V load(K key) {
        return database.get(key);
    }

    @Override
    public void delete(K key) {
        database.remove(key);
    }

}
