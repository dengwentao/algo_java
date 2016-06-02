package com.leetcode;
import java.util.*;

/**
 * LRU Cache
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * Created by wentaod on 6/1/16.
 */
public class LRUCache {
    static class Node {
        Node prev;
        Node next;
        int key;
        int value;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    int capacity;
    int count;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.head = new Node(-2, -2);
        head.prev = head;
        head.next = head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);

        if (count !=1) { // move used node to the last position
            // remove the node from the list
            node.prev.next = node.next;
            node.next.prev = node.prev;
            // insert the node to the end of list
            node.next = head;
            node.prev = head.prev;

            head.prev.next = node;
            head.prev = node;
        }

        return node.value;
    }

    public void set(int key, int value) {
        if (get(key) != -1) {
            map.get(key).value = value;
        } else {
            Node node = new Node(key, value);
            map.put(key, node);

            // put to the end
            node.next = head;
            node.prev = head.prev;
            head.prev.next = node;
            head.prev = node;

            if (capacity > count) {
                count++;
            } else {
                // remove the oldest
                Node removeNode = head.next;
                head.next = removeNode.next;
                head.next.prev = head;
                map.remove(removeNode.key);
            }
        }
    }

    static public void main(String args[]) {
        LRUCache cache = new LRUCache(5);
        cache.set(1, 11);
        cache.set(2, 22);
        cache.set(3, 33);
        System.out.println(cache.get(1));
        System.out.println(cache.get(4));
        cache.set(4, 44);
        cache.set(5, 55);
        System.out.println(cache.get(3));
        cache.set(6, 66);
        cache.set(7, 77);
        cache.set(8, 88);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
    }
}
