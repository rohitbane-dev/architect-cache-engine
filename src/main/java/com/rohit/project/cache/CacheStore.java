package com.rohit.project.cache;

import com.rohit.project.policy.eviction.AbstractEviction;

public class CacheStore<K, V> {
    public AbstractEviction<K, V> eviction;
    
    public CacheStore(AbstractEviction<K, V> eviction){
        this.eviction = eviction;
    }

    public V getValue(K key){
        return eviction.getValue(key);
    }

    public void put(K key, V value) {
        eviction.keyAdded(key, value);
    }
}
