package com.leetcode;
import java.lang.reflect.Array;
import java.util.*;

public class CombinationSum {
    /**
     * Combination Sum III
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     Ensure that numbers within the set are sorted in ascending order.
     * Created by wentaod on 11/17/15.
     */
    static public class SolutionIII {

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

    /**
     * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     The same repeated number may be chosen from C unlimited number of times.
     Note:
     1) All numbers (including target) will be positive integers.
     2) Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     3) The solution set must not contain duplicate combinations.
     */
    static public class Solution {
        int[] candidates;
        HashMap<Integer, HashSet<List<Integer>>> dp;

        static HashSet<List<Integer>> EMPTY; // empty combination
        static HashSet<List<Integer>> NONE; // no possible combination
        static {
            EMPTY = new HashSet<List<Integer>>();
            EMPTY.add(new LinkedList());
            NONE  = new HashSet<List<Integer>>();
            NONE.add(Arrays.asList(-1));
        }

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ret = new LinkedList<>();
            if(candidates.length == 0 || target <= 0)
                return ret;

            Arrays.sort(candidates);
            this.candidates = candidates;
            dp = new HashMap<>();

            HashSet<List<Integer>> result = comb(target, 0);
            if(result == NONE || result == EMPTY)
                return new LinkedList<>();

            return new LinkedList<>(result);
        }

        // starting from index of candidates[], find all combinations sum to target.
        // return NONE to indicate not possible.
        HashSet<List<Integer>> comb(int target, int index) {
            //System.out.println("target="+target+", index="+index);

            if(target == 0) {// found one
                return EMPTY;
            }
            else if(target < 0)
                return NONE; // not found
            else if(target > 0 && index == candidates.length)
                return NONE; // not found

            HashSet<List<Integer>> memo = dp.get(key(target, index));
            if(memo != null) { // already calculated
                return memo;
            }

            memo = new HashSet<>();
            HashSet<List<Integer>> skip = comb(target, index+1); // skip
            if(skip != NONE && skip != EMPTY)
                memo.addAll(skip);
            HashSet<List<Integer>> take1 = comb(target-candidates[index], index+1); // take 1 only
            if(take1 != NONE) {
                for(List<Integer> l : take1) {
                    List<Integer> ll = new LinkedList<>(l);
                    ll.add(0, candidates[index]);
                    memo.add(ll);
                }
            }
            HashSet<List<Integer>> take2 = comb(target-candidates[index], index); // take 2+
            if(take2 != NONE && take2 != EMPTY) {
                for(List<Integer> l : take2) {
                    List<Integer> ll = new LinkedList<>(l);
                    ll.add(0, candidates[index]);
                    memo.add(ll);
                }
            }

            if(skip==NONE && take1==NONE && take2==NONE)
                memo = NONE;

            dp.put(key(target, index), memo);
            return memo;
        }

        int key(int target, int index) {
            return target + (index<<16);
        }

        public void test() {
            List<List<Integer>> result = combinationSum(new int[]{5, 10, 15}, 20);
            for(List<Integer> l : result) {
                System.out.println(l);
            }
/*
            for(Map.Entry<Integer, HashSet<List<Integer>>> entry : dp.entrySet()) {
                System.out.println(entry.getKey() + " ----------------");
                for(List<Integer> l : entry.getValue())
                    System.out.println(l);
            }
*/
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
