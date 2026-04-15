package com.rohit.project.policy.eviction;

import java.util.Map;

import com.rohit.project.utility.Node;

public abstract class AbstractEviction<K, V> implements EvictionAlgorithm<K, V> {

    public Map<K, Node<K,V>> cacheStorageMap;
    public int capacity;

    public AbstractEviction(Map<K, Node<K,V>> cacheStorageMap, int capacity){
        this.capacity = capacity;
        this.cacheStorageMap = cacheStorageMap;
    }

    @Override
    public V getValue(K key){
        if(cacheStorageMap.containsKey(key)){
            Node<K, V> accessedKey = cacheStorageMap.get(key);
            callOnAcess(accessedKey);
            return accessedKey.value;
        }
        return null;
    }

    public synchronized void callOnAcess(Node<K, V> accessedKey){
        onAccess(accessedKey);
    }

    @Override
    public synchronized void keyAdded(K key, V value){
        if(cacheStorageMap.containsKey(key)){
            Node<K, V> node = cacheStorageMap.get(key);
            node.value = value;
            onAccess(node);
            return;
        }
        if(cacheStorageMap.size() >= capacity){
            Node<K, V> evictK = evict();
            if(evictK != null) cacheStorageMap.remove(evictK.key);
        }

        Node<K, V> newNode = new Node<K,V>(key, value);
        cacheStorageMap.put(key, newNode);
        onInsert(newNode);
    }

    public abstract void onAccess(Node<K,V> node);            //Eviction strategy accessed and update in dll
    public abstract void onInsert(Node<K,V> node);   //Eviction strategy wise insertion in dll
    public abstract Node<K, V> evict();                    //Eviction strategy wise eviction in dll
}
