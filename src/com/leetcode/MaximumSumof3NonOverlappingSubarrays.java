package com.leetcode;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

 Example:
 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 Note:
 nums.length will be between 1 and 20000.
 nums[i] will be between 1 and 65535.
 k will be between 1 and floor(nums.length / 3).
 * Created by wd186013 on 9/30/18.
 */
public class MaximumSumof3NonOverlappingSubarrays {

    static class Solution {

        int[] nums;
        int k;
        int[] prefixSum;

        static class S {
            int sum;
            int[] indexes;

            public S() {
                sum = 0;
                indexes = new int[3]; // hold start indexex of 3 subarrays
            }
        }

        S L(int c, int i) {
            if (c == 0 || i < c * k - 1 || i < 0)
                return new S();
            int sumK = prefixSum[i + 1] - prefixSum[i - k + 2];
            S s = G(c - 1, i - k);
            s.indexes[c - 1] = i - k + 1;
            s.sum += sumK;
            return s;
        }

        S G(int c, int i) {
            if (c == 0 || i < c * k - 1 || i < 0)
                return new S();
            S max = new S();
            for (int j = 0; j <= i; j ++) {
                S s = L(c, j);
                if (max.sum < s.sum) {
                    max = s;
                }
            }
            return max;
        }

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            if (nums == null || nums.length < 3 || k == 0)
                return null;

            this.nums = nums;
            this.k = k;
            this.prefixSum = new int[nums.length + 1];
            for (int i = 1; i <= nums.length; i ++)
                this.prefixSum[i] = this.prefixSum[i - 1] + nums[i - 1];

            S s = G(3, nums.length - 1);
            return s.indexes;
        }

        // not working yet.
        public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
            int a = 0, b = k, c = 2 * k;
            int max = Integer.MIN_VALUE;
            int maxA = 0, maxB = 0, maxC = 0;
            for (int i = 0; i < k; i ++) {
                maxA += nums[a + i];
                maxB += nums[b + i];
                maxC += nums[c + i];
            }
            max = maxA + maxB + maxC;
            int[] result = {a, b, c};
            for (int z = 0; z < nums.length - 3 * k; z ++) { // 3rd window can move steps
                c ++;
                maxA = maxA - nums[c - 1] + nums[c + k - 1];
                int cur = maxA + maxB + maxC;
                if (cur > max) {
                    max = cur;
                    result[0] = a;
                    result[1] = b;
                    result[2] = c;
                }
                b ++;
                maxB = maxB - nums[b - 1] + nums[b + k - 1];
                cur = maxA + maxB + maxC;
                if (cur > max) {
                    max = cur;
                    result[0] = a;
                    result[1] = b;
                    result[2] = c;
                }
                a ++;
                maxA = maxA - nums[a - 1] + nums[a + k - 1];
                cur = maxA + maxB + maxC;
                if (cur > max) {
                    max = cur;
                    result[0] = a;
                    result[1] = b;
                    result[2] = c;
                }
            }
            return result;
        }

        public void test() {
            int arr[] = {1,2,1,2,6,7,5,1};
            int k = 2;
            int[] result = maxSumOfThreeSubarrays(arr, k);
            for (int r : result)
                System.out.println(r);
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
