package com.leetcode;

/**
 * Insert into a Cyclic Sorted List
 * Given a node from a cyclic linked list which has been sorted, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be any single node in the list.
 * Created by wd186013 on 9/11/18.
 */
public class InsertIntoCyclicSortedList {

    static class Node {
        int v;
        Node next;
        public Node(int v) {
            this.v = v;
        }
    }

    static public void insert(Node head, int val) {
        Node p = head;
        do {
            if (p.v <= p.next.v) {
                if (p.v <= val && val <= p.next.v) {
                    break;
                }
            } else { // connection point
                if (p.v <= val || val <= p.next.v) {
                    break;
                }
            }
            p = p.next;
        } while (p != head);

        Node node = new Node(val);
        node.next = p.next;
        p.next = node;
    }

    static public void main(String args[]) {
        Node head = new Node(6);
        head.next = new Node(8);
        head.next.next = new Node(12);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(5);
        head.next.next.next.next.next.next = head;

        insert(head, 12);
        insert(head, 14);
        insert(head, 1);
        insert(head, 0);
        insert(head, 4);
        insert(head, 4);
        insert(head, 5);
        insert(head, 6);

        System.out.print(head.v + ", ");
        Node p = head.next;
        while (p != head) {
            System.out.print(p.v + ", ");
            p = p.next;
        }
    }
}
