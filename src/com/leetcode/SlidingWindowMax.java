package com.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Sliding Window Maximum
 * Created by wentaod on 9/11/15.
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the
 * very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 */
public class SlidingWindowMax {
    static public class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(k <= 0 || nums.length < k)
                return new int[0];

            int[] result = new int[nums.length - k + 1];
            Deque<Integer> maxDeq = new ArrayDeque<>();

            for (int i = 0; i < nums.length; i++) {
                while (!maxDeq.isEmpty() && nums[i] > nums[maxDeq.peekLast()])
                    maxDeq.pollLast();
                maxDeq.offerLast(i);

                if (i - maxDeq.peekFirst() >= k)
                    maxDeq.pollFirst();

                if(i >= k-1)
                    result[i-k+1] = nums[maxDeq.peekFirst()];
            }

            return result;
        }

        public void test() {
            int[] nums = {1,3,-1,-3,5,3,6,7};
            for(int i : maxSlidingWindow(nums, 3))
                System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.test();
    }
}
