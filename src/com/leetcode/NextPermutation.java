package com.leetcode;

/**
 * Next Permutation
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 The replacement must be in-place, do not allocate extra memory.
 * Created by wentaod on 12/9/15.
 */
public class NextPermutation {
    static public class Solution {
        public void nextPermutation(int[] nums) {
            if(nums==null || nums.length<2)
                return;

            int i=nums.length-1;
            for(; i>0; i--) {
                if(nums[i] > nums[i-1])
                    break;
            }

            if(i==0) // this is the largest permutation
                reverse(0, nums);
            else {
                // i-1 point to the one needs swap, and [i...end] are descending.
                reverse(i, nums);
                for(int j = i; j<nums.length; j++)
                    if(nums[j] > nums[i-1]) {
                        swap(i-1, j, nums);
                        break;
                    }
            }
        }

        void swap(int i, int j, int[] nums) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        // reverse nums in range [i...end]
        void reverse(int i, int[] nums) {
            int start = i;
            int end = nums.length - 1;
            while(start < end) {
                swap(start++, end--, nums);
            }
        }

        public void test() {
            int nums[] = {7, 2, 3, 6, 8, 4};
            nextPermutation(nums);
            for(int i : nums)
                System.out.print(i + ", ");
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
