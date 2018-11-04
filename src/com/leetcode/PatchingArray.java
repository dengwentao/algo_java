package com.leetcode;
import java.util.*;

/**
 * Patching Array
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
 * Example: nums = [1, 5, 10], n = 20
   Return 2. The two patches can be [2, 4].
 * Created by wentaod on 6/23/16.
 */
public class PatchingArray {
    static public class Solution {
        public int minPatches(int[] nums, int n) {
            if (nums == null || n < 1)
                return -1;

            int count = 0;
            int index = 0;
            for (long i = 1; i <= n; ) {
                if (index < nums.length && i >= nums[index]) { // next number is smaller, we can take it
                     i = i + nums[index ++]; // we can reach 2 * nums[index] - 1 after taking nums[index]
                } else { // we need i, but next number is unreachable, so we have to patch i
                    count ++;
                    i = 2 * i; // we can reach 2 * i - 1 after patching i
                }
            }

            return count;
        }

        public void test() {
            int[] array = {1, 2, 31, 33};
            int number = 2147483647;
            System.out.println(minPatches(array, number));
        }
    }

    static public void main(String[] args) {
        Solution solution = new Solution();
        solution.test();
    }
}
