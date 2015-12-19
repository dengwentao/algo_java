package com.leetcode;
import java.util.*;

/**
 * Subsets
 * Given a set of distinct integers, nums, return all possible subsets.
 Note:
 Elements in a subset must be in non-descending order.
 The solution set must not contain duplicate subsets.
 * Created by wentaod on 12/8/15.
 */
public class Subsets {
    static public class Solution {
        int[] nums;

        public List<List<Integer>> subsets(int[] nums) {
            Arrays.sort(nums);
            this.nums = nums;
            return subsets(0);
        }

        public List<List<Integer>> subsets(int index) {
            List<List<Integer>> result = new LinkedList<>();
            if(index == nums.length) {
                result.add(new LinkedList<Integer>());
                return result;
            }

            List<List<Integer>> previous = subsets(index+1);
            result.addAll(previous);

            for(List<Integer> l : previous) {
                List<Integer> ll = new LinkedList<>(l);
                ll.add(0, nums[index]);
                result.add(ll);
            }

            return result;
        }

        public void test() {
            int nums[] = {3, 1, 2};
            for(List<Integer> l : subsets(nums)) {
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
