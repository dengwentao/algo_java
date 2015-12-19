package com.leetcode;

import java.util.*;

/**
 * Permutation Sequence
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 By listing and labeling all of the permutations in order,
 We get the following sequence (ie, for n = 3):
 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 Given n and k, return the kth permutation sequence.
 Note: Given n will be between 1 and 9 inclusive.
 * Created by wentaod on 12/8/15.
 */
public class PermutationSequence {
    static public class Solution {
        StringBuilder result = new StringBuilder();
        List<Character> list = new LinkedList<>();
        int n;
        int k;
        int[] perms;

        public String getPermutation(int n, int k) {
            if(k<=0 || n<1 || n>9)
                return "";
            this.n = n;
            this.k = k;
            for(int i=1; i<=n; i++)
                list.add((char)('0' + i));
            perms = new int[n]; // perms[k] = k!
            perms[0] = 1;
            for(int i=1; i<n; i++) {
                perms[i] = i * perms[i-1];
            }
            if(k > n*perms[n-1])
                return "";
            permute();
            return result.toString();
        }

        void permute() {
            if(list.isEmpty())
                return;
            int digit = (k-1) / perms[n-1];
            result.append(list.remove(digit));
            k = ((k-1) % perms[n-1]) + 1;
            n--;
            permute();
        }

        public void test() {
            System.out.println(getPermutation(3, 4));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
