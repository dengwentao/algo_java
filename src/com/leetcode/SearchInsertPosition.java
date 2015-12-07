package com.leetcode;

/**
 * Search Insert Position
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 You may assume no duplicates in the array.
 * Created by wentaod on 12/6/15.
 */
public class SearchInsertPosition {
    static public class Solution {
        int[] nums;
        int target;
        public int searchInsert(int[] nums, int target) {
            this.nums = nums;
            this.target = target;
            if(nums.length == 0)
                return 0;
            return insert(0, nums.length-1);
        }

        int insert(int start, int end) {
            if(target <= nums[start])
                return start;
            if(target > nums[end])
                return end+1;

            int med = start + (end-start)/2;
            if(nums[med] < target) {
                return insert(med+1, end);
            }
            else if(nums[med] > target) {
                return insert(start, med-1);
            }
            else
                return med;
        }

        public void test() {
            int[] nums = {1,3,5,6};
            System.out.println(searchInsert(nums, 0));
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
