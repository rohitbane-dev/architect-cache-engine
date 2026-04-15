package com.rohit.project.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.rohit.project.policy.eviction.AbstractEviction;
import com.rohit.project.policy.eviction.impl.LFUEviction;
import com.rohit.project.policy.eviction.impl.LRUEviction;
import com.rohit.project.policy.write.WritePolicy;
import com.rohit.project.policy.write.impl.WriteThroughPolicy;
import com.rohit.project.storage.interfaces.DBStorage;
import com.rohit.project.utility.Node;

public class CacheMechanism<K,V> {
    public final CacheStore<K, V> store;
    public final WritePolicy<K, V> writePolicy;

    public CacheMechanism(String evictStrategy, String writeStrategy, int capacity, DBStorage<K, V> db){
        this.store = new CacheStore<>(getEviction(evictStrategy, capacity, new ConcurrentHashMap<>()));
        this.writePolicy = new WriteThroughPolicy<>(this.store, db);
    }

    private AbstractEviction<K, V> getEviction(String evictStrategy, int capacity,
            Map<K, Node<K, V>> cacheStorageMap) {
        AbstractEviction<K, V> eviction; 
        if("LFU".equalsIgnoreCase(evictStrategy)){
            eviction = new LFUEviction<>(cacheStorageMap, capacity);
        } else {
            eviction = new LRUEviction<>(cacheStorageMap, capacity);
        }
        return eviction;
    }
    

    public void updateEvictionStrategy(String evictStrategy){
        AbstractEviction<K, V> eviction  = this.store.eviction;
        Map<K, Node<K, V>> oldMap = eviction.cacheStorageMap;
        int capacity = eviction.capacity;

        this.store.eviction = getEviction(evictStrategy, capacity, oldMap);
    }

    public V getValue(K key){
        return store.getValue(key);
    }

    public void updateData(K key, V value) {
        store.put(key, value);
    }
}
