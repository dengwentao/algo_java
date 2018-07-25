package com.leetcode;

/**
 * Friends Of Appropriate Ages
 * Created by wd186013 on 7/19/18.
 *
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 age[B] <= 0.5 * age[A] + 7
 age[B] > age[A]
 age[B] > 100 && age[A] < 100
 Otherwise, A will friend request B.
 Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 How many total friend requests are made?
 */
public class FriendsOfAppropriateAges {

    static class BitTree {

        int[] bits = new int[256];

        public void add(int i, int x) {
            if (i >= 256)
                return;
            bits[i] += x;
            i += i & (-i);
            add(i, x);
        }

        public int sum(int i) {
            if (i <= 0)
                return 0;
            int s = bits[i];
            i -= i & (-i);
            return s + sum(i);
        }
    }

    static class Solution {
        public int numFriendRequests(int[] ages) {
            BitTree bits = new BitTree();
            for (int a : ages) {
                bits.add(a, 1);
            }

            int sum = 0;
            for (int a : ages) {
                if (a > 14) {
                    int low = a / 2 + 7; // exclusive
                    int high = a; // inclusive
                    sum += bits.sum(high) - bits.sum(low) - 1;
                }
            }

            return sum;
        }

        public void test() {
            System.out.println(numFriendRequests(new int[]{108,115,5,24,82}));
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.test();
    }
}
