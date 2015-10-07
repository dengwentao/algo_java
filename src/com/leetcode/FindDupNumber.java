package com.leetcode;

/**
 * Find the Duplicate Number
 * Created by wentaod on 10/2/15.
 * Given an array nums containing n + 1 integers where each integer is between 1 and n.
 * Assume that there is only one duplicate number, find the duplicate one.
 */
public class FindDupNumber {
    static public class Solution {
        public int findDuplicate(int[] nums) {
            if(nums == null || nums.length < 2)
                return 0;
            int start = 0; // start index
            int max = 0; // right most index been visited
            int step1 = 0, step2 = 0; // index
            int count1 = 0, count2 = 0; // count of steps
            while(true) {
                // start a new loop
                start = step1 = step2 = max + 1;
                count1 = count2 = 0;
                while(step1 < nums.length && step2 < nums.length) {
                    step1 = nums[step1] - 1;
                    count1 ++;
                    if(max < step1)
                        max = step1;
                    step2 = nums[step2] - 1;
                    if(max < step2)
                        max = step2;
                    step2 = nums[step2] - 1;
                    if(max < step2)
                        max = step2;
                    count2 += 2;
                    if(step1 == step2) {
                        // loop detected
                        if(step1 == start) // met a full loop without extra branch
                            break;
                        // met a loop starting from outside, now search for joint point
                        int loopLength = count2 - count1;
                        step1 = step2 = start;
                        for (int i=0; i< loopLength; i++) {
                            step1 = nums[step1] - 1;
                        }
                        while(step1 != step2) {
                            step1 = nums[step1] - 1;
                            step2 = nums[step2] - 1;
                        }
                        //already found the joint point, now the point before joint point is what we want.
                        int joint = step1;
                        int prev = start;
                        step1 = start;
                        while(joint != step1) {
                            prev = step1;
                            step1 = nums[step1] - 1;
                        }
                        return nums[prev];
                    }
                }
            }
        }

        public void test() {
            System.out.println(findDuplicate(new int[] {2,5,9,6,9,3,8,9,7,1}));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
