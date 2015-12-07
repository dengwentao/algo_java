package com.leetcode;

/**
 * Search in Rotated Sorted Array My Submissions Question
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 You are given a target value to search. If found in the array return its index, otherwise return -1.
 You may assume no duplicate exists in the array.
 * Created by wentaod on 12/6/15.
 */
public class SearchRotatedSortedArray {
    static public class Solution {
        int[] nums;
        int target;
        public int search(int[] nums, int target) {
            if(nums.length == 0)
                return -1;
            this.nums = nums;
            this.target = target;

            return search(0, nums.length-1);
        }

        int search(int low, int high) {
            if(high < low)
                return -1;

            int med = low + (high-low)/2;
            if(nums[med] < target) {
                if(target < nums[high])
                    return search(med+1, high-1);
                else if(target > nums[high]) {
                    int i1 = search(low, med - 1);
                    if(i1==-1)
                        return search(med+1, high-1);
                    else
                        return i1;
                }
                else
                    return high;
            }
            else if(nums[med] > target) {
                if(target < nums[low]) {
                    int i1 = search(med + 1, high);
                    if(i1==-1)
                        return search(low+1, med-1);
                    else
                        return i1;
                }
                else if(target > nums[low])
                    return search(low+1, med-1);
                else
                    return low;
            }
            else
                return med;
        }

        public void test() {
            int[] nums = {4, 5, 6, 7, 8, 1, 2, 3};
            System.out.println(search(nums, 8));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
