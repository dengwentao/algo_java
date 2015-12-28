package com.leetcode;

/**
 * Partition List
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 You should preserve the original relative order of the nodes in each of the two partitions.
 * Created by wentaod on 12/27/15.
 */
public class PartitionList {
    static  public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static public class Solution {
        public ListNode partition(ListNode head, int x) {
            if(head == null || head.next == null)
                return head;

            ListNode lowHead = null, lowTail = null, highHead = null, highTail = null;
            while(head != null) {
                if(head.val < x) {
                    if(lowHead == null) {
                        lowHead = head;
                        lowTail = head;
                    }
                    else {
                        lowTail.next = head;
                        lowTail = head;
                    }
                }
                else {
                    if(highHead == null) {
                        highHead = head;
                        highTail = head;
                    }
                    else {
                        highTail.next = head;
                        highTail = head;
                    }
                }
                head = head.next;
            }

            if(lowTail != null)
                lowTail.next = null;
            if(highTail != null)
                highTail.next = null;

            if(lowHead == null)
                lowHead = highHead;
            else
                lowTail.next = highHead;

            return lowHead;
        }

        public void test() {
            ListNode node1 = new ListNode(1);
            ListNode node2 = new ListNode(4);
            ListNode node3 = new ListNode(3);
            ListNode node4 = new ListNode(5);
            ListNode node5 = new ListNode(2);
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = node5;

            ListNode newHead = partition(node1, 3);
            while(newHead != null) {
                System.out.println(newHead.val);
                newHead = newHead.next;
            }
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
