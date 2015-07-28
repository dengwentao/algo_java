package com.leetcode;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wentaod on 7/28/15.
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 */
public class Majority {
    static class Solution {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> res = new LinkedList<>();
            if(nums.length == 0)
                return res;
            else if(nums.length == 1) {
                res.add(nums[0]);
                return res;
            }
            else if(nums.length == 2) {
                res.add(nums[0]);
                if(nums[0] != nums[1])
                    res.add(nums[1]);
                return res;
            }

            int count1 = 0;
            int count2 = 0;
            int maj1 = nums[0];
            int maj2 = nums[1];

            for(int i=0; i<nums.length; i++) {
                if(nums[i] == maj1)
                    count1++;
                else if(nums[i] == maj2)
                    count2++;
                else if(count1 == 0) {
                    count1 = 1;
                    maj1 = nums[i];
                }
                else if(count2 == 0) {
                    count2 = 1;
                    maj2 = nums[i];
                }
                else {
                    count1--;
                    count2--;
                }
            }

            System.out.println("maj1=" + maj1 + " maj2=" + maj2 + " nums.length/3=" + nums.length / 3);

            if(count(maj1, nums) > nums.length / 3)
                res.add(maj1);
            if(maj1!=maj2 && count(maj2, nums) > nums.length / 3)
                res.add(maj2);
            return res;
        }

        private int count(int n, int[] nums) {
            int cnt = 0;
            for(int i : nums) {
                if(n == i)
                    cnt++;
            }
            return cnt;
        }

        public void test() {
            int[] a = {3,5,1,4,1,9,1,8,1,7};
            //int[] a = {2,5,1,4,1,2,1,2};
            //int[] a = {2,5,1,4,1,2,1,2,8};
            //int[] a = {4,2,1,1};
            //int[] a = {1,1,1,3,3,2,2,2};
            for(int i : majorityElement(a))
                System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
