package com.leetcode;

/**
 * Add Two Numbers
 * Created by wentaod on 8/13/15.
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order
 * and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 */
public class Add2List {

     static public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

    static public class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(-1);
            ListNode p = result;
            int carry = 0;
            while(l1!=null || l2!=null || carry!=0) {
                int a = 0;
                if(l1 != null) {
                    a = l1.val;
                    l1 = l1.next;
                }
                int b = 0;
                if(l2 != null) {
                    b = l2.val;
                    l2 = l2.next;
                }
                int c = a + b + carry;
                if(c>9) {
                    carry = 1;
                    c = c % 10;
                }
                else
                    carry = 0;
                p.next = new ListNode(c);
                p = p.next;
            }
            return result.next;
        }

        private ListNode createList(int n) {
            ListNode dock = new ListNode(-1);
            ListNode p = dock;
            while(n>0) {
                p.next = new ListNode(n%10);
                n = n/10;
                p = p.next;
            }
            return dock.next;
        }

        private boolean verifyList(ListNode result, int res) {
            return res == decode(result);
        }

        private int decode(ListNode l) {
            ListNode p = l;
            int result = 0;
            int pow = 1;
            while(p != null) {
                result += p.val * pow;
                pow *= 10;
                p = p.next;
            }
            return result;
        }

        public void test (int n1, int n2) {
            ListNode l1 = createList(n1);
            ListNode l2 = createList(n2);
            ListNode result = addTwoNumbers(l1, l2);
            if(!verifyList(result, n1+n2))
                System.out.println("Input "+n1+" and "+n2+", expect "+(n1+n2)+" but got "+decode(result));
            else
                System.out.println("Input "+n1+" and "+n2+", expect "+(n1+n2)+" and got it successfully.");
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.test(0, 0);
        sol.test(1, 0);
        sol.test(1, 9);
        sol.test(11, 999);
        sol.test(342, 465);
    }
}
