package com.leetcode;

/**
 * Range Sum Query
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 The update(i, val) function modifies nums by updating the element at index i to val.
 * Created by wentaod on 11/20/15.
 */
public class RangeSumQuery {
    static public class NumArray {

        int[] nums;
        int[] bit;


        public NumArray(int[] nums) {
            this.nums = nums;
            bit = new int[nums.length+1];
            // at this moment, BIT is all 0's. The assumption here is all array elements are 0, so we need to call update for give them correct values.
            for(int i=0; i<nums.length; i++)
                updateDiff(i, nums[i]);

            for(int i=0; i<=nums.length; i++)
                System.out.println(bit[i]);
        }

        // sum of elements from 0 to x.
        private int sumTo(int x) {
            int sum = 0;
            x += 1;
            while(x>0) {
                sum += bit[x];
                x -= x&(-x);
            }
            return sum;
        }

        // add nums[x] by diff
        private void updateDiff(int x, int diff) {
            x += 1;
            while(x<=nums.length) {
                bit[x] += diff;
                x += x&(-x);
            }
        }

        // update nums[x] to val
        public void update(int x, int val) {
            updateDiff(x, val - nums[x]);
            nums[x] = val;
        }

        // sum from nums[i..j] inclusive
        public int sumRange(int i, int j) {
            return sumTo(j) - sumTo(i-1);
        }

    }

    static public void main(String args[]) {
        int[] nums = {3, 5, 1, 7, 9, 4};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 1));
        numArray.update(1, 10);
        numArray.update(2, 10);
        numArray.update(3, 10);
        numArray.update(1, 5);
        System.out.println(numArray.sumRange(1, 2));
        System.out.println(numArray.sumRange(3, 4));
    }
}
