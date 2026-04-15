package com.rohit.project.utility;

public class DoubleLinkedList<K, V> {

    Node<K, V> head;
    Node<K, V> tail;
    int size;

    public DoubleLinkedList() {
        this.head = new Node<K, V>(null, null);
        this.tail = new Node<K, V>(null, null);

        head.next = tail;
        tail.prev = head;
    }

    public void addNodeAtTail(Node<K, V> node) {
        node.next = tail;
        node.prev = tail.prev;

        tail.prev.next = node;
        tail.prev = node;
        size++;

    }

    public void detachNode(Node<K,V> node) {
        if (node == null) return;
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
        size--;
    }

    public Node<K,V> getHead() {
        if(head.next == tail) return null;
        return head.next;
    }

    public Node<K,V> getTail() {
        if(tail.prev == head ) return null;
        return tail.prev;
    }

    public Node<K,V> removeHead() {
        if(head.next == tail) return null;
        Node<K, V> currHead = head.next;
        detachNode(currHead);
        return currHead;
    }

    public boolean isEmpty(){
        return size==0;
    }
}
