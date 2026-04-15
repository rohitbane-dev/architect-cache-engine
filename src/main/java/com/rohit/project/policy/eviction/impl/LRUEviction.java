package com.rohit.project.policy.eviction.impl;

import java.util.Map;

import com.rohit.project.policy.eviction.AbstractEviction;
import com.rohit.project.utility.DoubleLinkedList;
import com.rohit.project.utility.Node;

public class LRUEviction<K,V> extends AbstractEviction<K, V> {
    private final DoubleLinkedList<K, V> dll;

    public LRUEviction(Map<K, Node<K,V>> cacheStorageMap, int capacity) {
        super(cacheStorageMap, capacity);
        this.dll = new DoubleLinkedList<K,V>();
    }

    @Override
    public void onAccess(Node<K, V> node) {
        dll.detachNode(node);
        dll.addNodeAtTail(node);
    }

    @Override
    public void onInsert(Node<K, V> node){
        dll.addNodeAtTail(node);
    }

    @Override
    public Node<K, V> evict(){
        return dll.removeHead();
    }

}
