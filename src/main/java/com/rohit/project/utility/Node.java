package com.rohit.project.utility;

public class Node<K, V> {

    public K key;
    public V value;
    public int freq;
    public Node<K, V> prev;
    public Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public int getFreq() {
        return freq;
    }

}
