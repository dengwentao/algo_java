package com.leetcode;

/**
 * Reverse Nodes in k-Group
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 You may not alter the values in the nodes, only nodes itself may be changed.
 Only constant memory is allowed.
 * Created by wentaod on 12/20/15.
 */
public class ReverseNodesInKGroup {

    static  public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static public class Solution {

        //// Solution I - iterative

        ListNode newHead; // new head of the list after being fully processed.
        ListNode remainder; // first node of a unprocessed remaining list
        ListNode tail; // last node of a fully reversed k list

        public ListNode reverseKGroupIteratively(ListNode head, int k) {
            if(k<=0 || head==null)
            return null;
            if(k==1 || head.next==null)
                return head;

            remainder = head;
            tail = head; // when reversed, the first node will become the last

            boolean first = true;
            while(remainder != null) {
                ListNode lastTail = tail;
                ListNode reverseHead = reverseK(remainder, k);
                if(first) {
                    this.newHead = reverseHead;
                    first = false;
                }
                else
                    lastTail.next = reverseHead;
            }

            return newHead;
        }

        // reverse first k nodes, after processing, 2 lists are created. One is fully reversed k nodes that is returned,
        // and this.tail points to the tail of this list.
        // Another is the unchanged remaining part pointed by this.remainder.
        // If there're less than k nodes, do nothing and this.remainder is null.
        ListNode reverseK(ListNode head, int k) {
            ListNode node = head;
            int count = 0;
            while(node != null) {
                count++;
                if(count == k) {
                    tail = head; // now that we've find first k nodes, the head will become tail after being fully reversed.
                    remainder = node.next; // record head of remaining part.
                    node.next = null; // break into two list
                    return reverseFull(head);
                }
                node = node.next;
            }

            // less than k nodes, do nothing.
            remainder = null;
            return head;
        }

        // Fully reverse a linked list. Return head of new list.
        ListNode reverseFull(ListNode head) {
            // Each loop, remove head from the old list and insert to the head of new list.
            ListNode newHead = null; // head of the new list.
            while(head != null) {
                ListNode prev = head;
                head = head.next; // remove head from the old list.
                prev.next = newHead; // insert node prev into the head of new list.
                newHead = prev;
            }
            return newHead;
        }

        //// Solution II - recursive

        public ListNode reverseKGroupRecursively(ListNode head, int k) {
            if(k<=0 || head==null)
                return null;
            if(k==1 || head.next==null)
                return head;

            int count = 0;
            ListNode node = head;
            while(node != null) {
                count++;
                if(count % k == 0) {
                    ListNode nextHead = node.next;
                    node.next = null;
                    ListNode newHead = reverseFullRecursively(head);
                    head.next = reverseKGroupRecursively(nextHead, k);
                    return newHead;
                }
                node = node.next;
            }

            return head;
        }

        ListNode reverseFullRecursively(ListNode head) {
            if(head == null || head.next == null)
                return head;
            ListNode next = head.next;
            ListNode newHead = reverseFullRecursively(next);
            next.next = head;
            head.next = null;
            return newHead;
        }

        //// Solution III - iterative, a new way
        // First fully reverse k groups, and then reverse the full list:
        /* Example:
        1, 2, 3, 4, 5, 6, 7
        7, 5, 6, 3, 4, 1, 2
        2, 1, 4, 3, 6, 5, 7
        * */
        public ListNode reverseKGroup(ListNode head, int k) {
            if(k<=0 || head==null)
                return null;
            if(k==1 || head.next==null)
                return head;

            return reverseFull(revK(head, k));
        }

        ListNode revK(ListNode head, int k) {
            ListNode newHead = null; // head of new list
            ListNode node = head;
            int count = 0;
            while(node != null) {
                count++;
                if(count % k == 0) {
                    ListNode oldHead = head;
                    head = node.next; // head point to remaining list
                    node.next = newHead; // the first k nodes list is inserted to the head of new list
                    newHead = oldHead;
                    node = head;
                    continue;
                }
                node = node.next;
            }
            if(count % k != 0) {
                // remaining less than k
                ListNode remainder = reverseFull(head);
                head.next = newHead;
                newHead = remainder;
            }
            return newHead;
        }


        public void test() {
            ListNode node1 = new ListNode(1);
            ListNode node2 = new ListNode(2);
            ListNode node3 = new ListNode(3);
            ListNode node4 = new ListNode(4);
            //ListNode node5 = new ListNode(5);
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            ///node4.next = node5;
            ListNode node = reverseKGroup(node1, 5);
            while(node != null) {
                System.out.println(node.val);
                node = node.next;
            }
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
