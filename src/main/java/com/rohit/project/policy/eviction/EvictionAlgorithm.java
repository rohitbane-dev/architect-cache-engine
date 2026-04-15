package com.rohit.project.policy.eviction;

import com.rohit.project.utility.Node;

public interface EvictionAlgorithm<K, V> {

    public V getValue(K key);              //common method in abstract form
    public void keyAdded(K key, V value);   //common method in abstract form
    public void onAccess(Node<K, V> node);            //Eviction strategy accessed and update in dll
    public void onInsert(Node<K, V> node);   //Eviction strategy wise insertion in dll
    public Node<K, V> evict();                    //Eviction strategy wise eviction in dll
}
