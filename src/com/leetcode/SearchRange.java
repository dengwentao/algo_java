package com.leetcode;

/**
 * Search for a Range
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 Your algorithm's runtime complexity must be in the order of O(log n).
 If the target is not found in the array, return [-1, -1].
 * Created by wentaod on 12/6/15.
 */
public class SearchRange {
    static public class Solution {
        int[] nums;
        int target;

        public int[] searchRange(int[] nums, int target) {
            this.nums = nums;
            this.target = target;
            int left = range(0, nums.length-1, true);
            int right = range(0, nums.length-1, false);
            return new int[]{left, right};
        }

        // left means the left boundary in nums[low, high], else right.
        int range(int low, int high, boolean left) {
            if(high < low)
                return -1;
            if(low == high) {
                if(target == nums[low])
                    return low;
            }

            int med = low + (high - low) / 2;
            if(target < nums[med]) {
                return range(low, med-1, left);
            }
            else if(target > nums[med]) {
                return range(med+1, high, left);
            }
            else {
                if(left)
                    return range(low, med, left);
                else {
                    if(high == low+1) // in this case med == low.
                        return nums[high]==target ? high : low;
                    else
                        return range(med, high, left);
                }
            }
        }

        public void test() {
            int[] nums = {8};
            int[] range = searchRange(nums, 9);
            System.out.println(range[0] + ", " + range[1]);
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
