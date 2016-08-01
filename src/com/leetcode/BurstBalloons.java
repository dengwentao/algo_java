package com.leetcode;
import java.util.*;

/**
 * Burst Balloons
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 Find the maximum coins you can collect by bursting the balloons wisely.
 Note:
 (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 Example: Given [3, 1, 5, 8], Return 167
 nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * Created by wentaod on 7/27/16.
 */
public class BurstBalloons {
    static public class Solution {
        int[] nums;
        Map<Integer, Integer> dp;

        public int maxCoins(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;
            this.nums = nums;
            dp = new HashMap<>();
            return maxCions(1, 0, nums.length - 1, 1);
        }

        int key(int left, int start, int end, int right) {
            return (left << 24) + (right << 16) + (start << 8) + end;
        }

        // calculate the max for array {left, [start, end], right}
        // left & right are neighbors to the left and right of the array.
        int maxCions(int left, int start, int end, int right) {
            if (start > end)
                return 0;

            int key = key(left, start, end, right);
            if (dp.get(key) != null)
                return dp.get(key);

            int max = Integer.MIN_VALUE;

            for (int i = start; i <= end; i++) {
                int value = left * nums[i] * right + maxCions(left, start, i - 1, nums[i]) + maxCions(nums[i], i + 1, end, right);
                if (value > max)
                    max = value;
            }

            dp.put(key, max);
            return max;
        }

        public void test() {
            int[] array = {3, 1, 5, 8};
            System.out.println(maxCoins(array));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
