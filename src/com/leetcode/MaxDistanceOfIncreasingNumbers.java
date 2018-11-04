package com.leetcode;

import Tests.Array2D;
import java.util.*;

/**
 * Maximum Distance of 2 Increasing Elements
 * Given an array A of integers, find the maximum of j-i subjected to the constraint of A[i] < A[j].
 * Created by wentaod on 12/16/15.
 */
public class MaxDistanceOfIncreasingNumbers {
    static public class Solution {

        static class Pair implements Comparable<Pair> {
            int value;
            int index;
            public Pair(int value, int index) {
                this.value = value;
                this.index = index;
            }
            public int compareTo(Pair p) {
                return value - p.value;
            }
        }

        public int maximumGap3(int[] nums) {
            if(nums == null || nums.length < 2)
                return 0;
            Pair[] pairs = new Pair[nums.length];
            for(int i=0; i<nums.length; i++) {
                pairs[i] = new Pair(nums[i], i);
            }
            Arrays.sort(pairs);
            int[] indice = new int[nums.length];
            for(int i=0; i<nums.length; i++)
                indice[i] = pairs[i].index;

            int max = 0;
            int minIndex = indice[0];
            for (int i = 1; i < indice.length; i ++) {
                int gap = indice[i] - minIndex;
                if (gap > max) {
                    max = gap;
                } else {
                    minIndex = indice[i];
                }
            }
            return max;
        }

        public void test() {
            int[] arr = {9, 6, 8, 1, 0, 2, 12, 3};
            System.out.println(maximumGap3(arr));
        }

    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
