package com.leetcode;

import java.util.*;
import Challenges.FindKth;

/**
 * Wiggle Sort II
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 Example:
 (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 Note:
 You may assume all input has valid answer.
 * Created by wentaod on 8/9/16.
 */
public class WiggleSort {
    static public class Solution {
        public void wiggleSort(int[] nums) {
            if (nums == null || nums.length < 2) {
                return;
            }

            int med = FindKth.findKth(nums, nums.length/2);
            System.out.println("Med is " + med);
            print(nums);
            threeWayPartition(nums, med);
            print(nums);
            rewire(nums);
            print(nums);
        }

        void threeWayPartition(int[] nums, int med) {
            int lowTop = 0;
            int medTop = 0;
            int highBtm = nums.length - 1;

            while (medTop < highBtm) {
                if (nums[medTop] < med) {
                    swap(nums, medTop++, lowTop++);
                } else if (nums[medTop] == med) {
                    medTop++;
                } else {
                    swap(nums, medTop, highBtm--);
                }
            }
        }

        void swap(int[] nums, int i, int j) {
            int x = nums[i];
            nums[i] = nums[j];
            nums[j] = x;
        }

        // rearrange the 3-partitioned numbers into wiggle format.
        // first cut into 2 halves. we should insert right half to odd slots and left to even.
        // if we have multiple medians, we want them to be as far as possible, so we insert in reversed order -
        // first of right half goes to the right most, and last of left goes to left most.
        void rewire(int[] nums) {
            int[] copy = Arrays.copyOf(nums, nums.length);
            int cutIdx = (nums.length - 1) / 2 + 1;
            for (int i=cutIdx-1, slot=0; i>=0; i--, slot+=2) // smaller half to even slots, starting from right most even slot
                nums[slot] = copy[i];
            for (int i=nums.length-1, slot=1; i>=cutIdx; i--, slot+=2)
                nums[slot] = copy[i];
        }

        void print(int[] nums) {
            for (int i : nums)
                System.out.print(i + ", ");
            System.out.println();
        }

        public void test() {
            int[] nums = {1,3,2,2,2,1,1,3,1,1,2};
            wiggleSort(nums);
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
