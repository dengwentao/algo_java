package com.leetcode;
import java.util.*;

/**
 * Smallest Range II
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).
 After this process, we have some array B.
 Return the smallest possible difference between the maximum value of B and the minimum value of B.

 Input: A = [1,3,6], K = 3
 Output: 3
 Explanation: B = [4,6,3]

 * Created by wd186013 on 9/26/18.
 */
public class SmallestRange2 {
    static class Solution {
        static public int smallestRangeII(int[] A, int K) {
            Arrays.sort(A);
            int n = A.length, mx = A[n - 1], mn = A[0], res = mx - mn;
            for (int i = 0; i < n - 1; ++i) {
                mx = Math.max(mx, A[i] + 2 * K);
                mn = Math.min(A[i + 1], A[0] + 2 * K);
                res = Math.min(res, mx - mn);
            }
            return res;
        }
    }

    public static void main(String args[]) {
        int[] arr = {3, 6, 8, 1, 3, 5, 3};
        System.out.print(Solution.smallestRangeII(arr, 5));
    }
}
