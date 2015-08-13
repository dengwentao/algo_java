package com.leetcode;
import java.util.*;

/**
 * Two Sum
 * Created by wentaod on 8/13/15.
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 The function twoSum should return indices of the two numbers such that they add up to the target,
 where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 You may assume that each input would have exactly one solution.
 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2
 */
public class TwoSum {
    static public class Solution {
        static class Pair implements Comparable<Pair> {
            int index;
            int value;

            public Pair(int i, int v) {
                index = i;
                value = v;
            }

            public int compareTo(Pair other) {
                return this.value - other.value;
            }
        }

        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length < 2)
                return new int[0];

            Pair[] pairs = new Pair[nums.length];
            for (int i = 0; i < nums.length; i++) {
                pairs[i] = new Pair(i + 1, nums[i]);
            }
            Arrays.sort(pairs);

            int i = 0, j = nums.length - 1;
            while (i < j) {
                int sum = pairs[i].value + pairs[j].value;
                if (sum < target)
                    i++;
                else if (sum > target)
                    j--;
                else {
                    int[] result = new int[2];
                    result[0] = Math.min(pairs[i].index, pairs[j].index);
                    result[1] = Math.max(pairs[i].index, pairs[j].index);
                    return result;
                }
            }
            return new int[0];
        }

        public void test() {
            int[] numbers = {3, 2, 4};
            int target = 6;
            int[] result = twoSum(numbers, target);
            for (int index : result) {
                System.out.println(index);
            }
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
