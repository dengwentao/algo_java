package com.leetcode;
import java.lang.Math.*;
import java.util.*;

/**
 * Contains Duplicate III
 * Created by wentaod on 9/11/15.
 */
public class BiggestGap {

    //* Given an array of integers, find out whether there are *two distinct* indices i and j in the array
    //* such that the difference between nums[i] and nums[j] is *larger than* t and the difference between i and j is at most k.
    static public class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if(t < 0 || k < 0)
                return false;
            if(k == 0)
                return false;
            if(nums.length < 2)
                return false;

            Deque<Integer> maxDeq = new ArrayDeque<>();
            maxDeq.offer(0);
            Deque<Integer> minDeq = new ArrayDeque<>();
            minDeq.offer(0);

            for(int i=1; i<nums.length; i++) {
                if(i - maxDeq.peekFirst() > k)
                    maxDeq.pollFirst();
                while(!maxDeq.isEmpty() && nums[i] > nums[maxDeq.peekLast()])
                    maxDeq.pollLast();
                maxDeq.offerLast(i);

                if(i - minDeq.peekFirst() > k)
                    minDeq.pollFirst();
                while(!minDeq.isEmpty() && nums[i] < nums[minDeq.peekLast()])
                    minDeq.pollLast();
                minDeq.offerLast(i);

                int gap = nums[maxDeq.peekFirst()] - nums[minDeq.peekFirst()];
                System.out.println("Index: " + i + ", Max: " + nums[maxDeq.peekFirst()] + ", Min: " + nums[minDeq.peekFirst()] + ", Gap: " + gap);

                if(gap > t)
                    return false;
            }

            return true;
        }

        private boolean validate(int[] nums, int k, int t) {
            boolean res = containsNearbyAlmostDuplicate(nums, k, t);
            System.out.println("gap of indexes is at most " + k + " and gap between numbers are at most " + t + " -- " + res);
            return res;
        }

        public void test() {
            int[] nums = {1, 3, 1};
            //int[] nums = {5, 2, 7, 9, 5, 1, 7, 9, 3};
            //validate(new int[0], 0, 0);
            //validate(nums, -1, 7);
            //validate(nums, 0, 0);
            //validate(nums, 0, 1);
            validate(nums, 2, 2);
            //validate(nums, 1, 4);
            //validate(nums, 2, 1);
        }

    }

    //* Given an array of integers, find out whether there are *two distinct* indices i and j in the array
    //* such that the difference between nums[i] and nums[j] is *at most* t and the difference between i and j is at most k.
    static public class SolutionII {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if(nums==null || nums.length<2 || k==0 || t<0)
                return false;
            HashMap<Long, Long> bucks = new HashMap<>();
            for(int i=0; i<nums.length; i++) {
                // window size exceeds, we need to remove 1 left element
                if(i > k) {
                    long remappedNumLeft = (long)nums[i-k-1] - Integer.MIN_VALUE;
                    long bucketLeft = remappedNumLeft / (t+1);
                    bucks.remove(bucketLeft);
                }

                // add 1 right element into window
                long remappedNum = (long)nums[i] - Integer.MIN_VALUE;
                long bucket = remappedNum / (t+1);
                if (bucks.containsKey(bucket) || bucks.containsKey(bucket-1) && Math.abs(bucks.get(bucket-1) - remappedNum)<=t
                        || bucks.containsKey(bucket+1) && Math.abs(bucks.get(bucket+1) - remappedNum)<=t)
                    return true;
                bucks.put(bucket, remappedNum);
            }
            return false;
        }

        private boolean validate(int[] nums, int k, int t) {
            boolean res = containsNearbyAlmostDuplicate(nums, k, t);
            System.out.println("gap of indexes is at most " + k + " and gap between numbers are at most " + t + " -- " + res);
            return res;
        }

        public void test() {
            int[] nums = {-1,2147483647};//{1, -3, 6};
            validate(nums, 1, 2147483647);
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
