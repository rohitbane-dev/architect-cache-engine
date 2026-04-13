package com.rohit.project.storage.interfaces;

public interface DBStorage<K, V> {
    void save(K key, V value);
    V load(K key);
    void delete(K key);

}
