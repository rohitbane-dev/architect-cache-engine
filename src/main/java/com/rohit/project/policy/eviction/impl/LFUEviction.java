package com.rohit.project.policy.eviction.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.rohit.project.policy.eviction.AbstractEviction;
import com.rohit.project.utility.DoubleLinkedList;
import com.rohit.project.utility.Node;

public class LFUEviction<K, V> extends AbstractEviction<K,V> {

    public final Map<Integer, DoubleLinkedList<K, V>> freqMap;
    public int minFreq;

    public LFUEviction(Map<K, Node<K,V>> cacheStorageMap, int capacity){
        super(cacheStorageMap, capacity);
        this.freqMap = new ConcurrentHashMap<>();
    }

    @Override
    public void onAccess(Node<K, V> node) {
        int oldFreq = node.freq;
        node.freq++;
        freqMap.get(oldFreq).detachNode(node);

        if(freqMap.get(oldFreq).isEmpty() && minFreq==oldFreq){
            minFreq++;
        }

        freqMap.computeIfAbsent(node.freq, k -> new DoubleLinkedList<>()).addNodeAtTail(node);
    }

    @Override
    public void onInsert(Node<K, V> node) {
        minFreq= node.freq;
        freqMap.computeIfAbsent(node.freq, k -> new DoubleLinkedList<>()).addNodeAtTail(node);
    }

    @Override
    public Node<K, V> evict() {
        return freqMap.get(minFreq).removeHead();
    }
}
