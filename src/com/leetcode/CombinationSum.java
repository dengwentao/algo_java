package com.leetcode;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Combination Sum III
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 Ensure that numbers within the set are sorted in ascending order.
 * Created by wentaod on 11/17/15.
 */
public class CombinationSum {
    static public class Solution {

        public List<List<Integer>> combinationSum3(int k, int n) {
            if(k<=0 || n<=0 || n<k || n>9*k)
                return new LinkedList<>();
            return combine(1, k, n);
        }

        // returns a list of lists each of which 1) starts from start; 2) in increasing order; 3) has k numbers; 4) sum to n.
        private List<List<Integer>> combine(int start, int k, int n) {
            List<List<Integer>> result = new LinkedList<List<Integer>>();

            if(k<=0 || n<=0 || n<k || n>9*k || n<start || start<1 || start>9)
                return result;

            if(k==1) {
                List<Integer> l = new LinkedList<>();
                l.add(0, n);
                result.add(l);
                return result;
            }

            for(int i=start; i<=9; i++) {
                List<List<Integer>> next = combine(i+1, k-1, n-i);
                for(List<Integer> l : next) {
                    l.add(0, i);
                    result.add(l);
                }
            }
            return result;
        }

        public void test() {
            List<List<Integer>> result = combinationSum3(3, 6);
            for(List<Integer> l : result) {
                System.out.println(l);
            }
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
