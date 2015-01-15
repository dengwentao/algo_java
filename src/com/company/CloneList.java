package com.company;

/**
 * Created by wedeng on 1/14/15.
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

 Return a deep copy of the list.
 */

class RandomListNode {
     int label;
     RandomListNode next, random;
     RandomListNode(int x) { this.label = x; }
};

class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)
            return null;

        RandomListNode p = head;
        while(p!=null)
        {
            RandomListNode node = new RandomListNode(p.label);
            node.next = p.next;
            p.next = node;
            p = node.next;
        }

        p = head;
        while(p!=null)
        {
            p.next.random = p.random==null ? null : p.random.next;
            p = p.next.next;
        }

        p = head;
        RandomListNode head2 = p.next;
        RandomListNode p2 = head2;
        while(p!=null)
        {
            p.next = p2.next;
            p = p.next;
            p2.next = p==null ? null : p.next;
            p2 = p2.next;
        }

        return head2;
    }

    public void display(RandomListNode head)
    {
        while(head != null)
        {
            System.out.format("%d -- %d\n", head.label, (head.random==null ? -1 : head.random.label));
            head = head.next;
        }
    }

    public void test()
    {
        RandomListNode head = new RandomListNode(0);
        RandomListNode p = head;
        for(int i=1; i<5; i++) {
            p.next = new RandomListNode(i);
            p = p.next;
        }
        p.next = null;
        head.random = head.next.next;
        head.next.random = head.next.next.next;
        head.next.next.random = head.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head.next.next.next.next;

        RandomListNode head2 = copyRandomList(head);
        display(head);
        display(head2);
    }
}

public class CloneList {
    public static void main(String[] args) {
        // write your code here
        Solution sol = new Solution();
        sol.test();
    }
}
