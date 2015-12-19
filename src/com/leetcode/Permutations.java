package com.leetcode;
import java.util.*;

/**
 * Permutations
 * Given a collection of numbers, return all possible permutations.
 * Created by wentaod on 12/9/15.
 */
public class Permutations {
    static public class SolutionII {
        int[] nums;
        public List<List<Integer>> permute(int[] nums) {
            if(nums==null || nums.length==0)
                return new LinkedList<>();
            this.nums = nums;
            return permute(0);
        }

        List<List<Integer>> permute(int index) {
            List<List<Integer>> result = new LinkedList<>();
            if(index == nums.length) {
                result.add(new LinkedList<Integer>());
                return result;
            }

            for(List<Integer> l : permute(index+1)) {
                for(int i=0; i<=l.size(); i++) {
                    List<Integer> ll = new LinkedList<>(l);
                    ll.add(i, nums[index]);
                    result.add(ll);
                }
            }
            return result;
        }

        public void test() {
            int[] arr = {1, 2, 3};
            for(List<Integer> l : permute(arr)) {
                for(int i : l)
                    System.out.print(i + ", ");
                System.out.println();
            }
        }
    }

/*
Permutations II
Given a collection of numbers that might contain duplicates, return all possible unique permutations.
For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/
    static public class Solution {
        int[] nums;
        public List<List<Integer>> permuteUnique(int[] nums) {
            if(nums==null || nums.length==0)
                return new ArrayList<>();
            this.nums = nums;
            Set<List<Integer>> perms = permute(0);
            return new ArrayList<>(perms);
        }

        Set<List<Integer>> permute(int index) {
            Set<List<Integer>> result = new HashSet<>();
            if(index == nums.length) {
                result.add(new LinkedList<Integer>());
                return result;
            }

            for(List<Integer> l : permute(index+1)) {
                for(int i=0; i<=l.size(); i++) {
                    List<Integer> ll = new LinkedList<>(l);
                    ll.add(i, nums[index]);
                    result.add(ll);
                }
            }
            return result;
        }

        public void test() {
            int[] arr = {1, 1, 3};
            for(List<Integer> l : permuteUnique(arr)) {
                for(int i : l)
                    System.out.print(i + ", ");
                System.out.println();
            }
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
