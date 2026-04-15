package com.rohit.project.policy.write;

public interface WritePolicy<K, V> {
    void write(K key, V value) throws Exception;

}
