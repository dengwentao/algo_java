package com.leetcode;
import com.sun.prism.shader.AlphaOne_Color_Loader;

import java.util.*;

/**
 * Create Maximum Number
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

 Example 1:
 nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]
 k = 5
 return [9, 8, 6, 5, 3]

 Example 2:
 nums1 = [6, 7]
 nums2 = [6, 0, 4]
 k = 5
 return [6, 7, 6, 0, 4]

 Example 3:
 nums1 = [3, 9]
 nums2 = [8, 9]
 k = 3
 return [9, 8, 9]
 * Created by wentaod on 6/2/16.
 */

public class CreateMaximumNumber {
    static public class Solution {
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            if(k <=0 || nums1.length + nums2.length < k)
                return new int[0];
            int[] result = new int[k];
            for(int i=0, j=k; i<=k && i<=nums1.length; i++, j--) { // i is size of max array to pick from nums1
                if(j > nums2.length)
                    continue;
                int[] maxArray1 = maxArray(nums1, i);
                int[] maxArray2 = maxArray(nums2, j);
                int[] tmp = merge(maxArray1, maxArray2);
                if(compare(result, 0, tmp, 0) < 0)
                    result = tmp;
            }
            return result;
        }

        // pick k elements from an array and the result should be the maximum.
        int[] maxArray(int[] a, int k) {
            if(k<=0 || k>a.length)
                return new int[0];

            int[] result = new int[k];
            int start = 0;
            int end = a.length - k; // we can only pick the max in range [start, end]
            for(int i=0; i<k; i++) {
                int max = -1;
                int maxIndex = -1;
                for(int j=start; j<=end; j++) {
                    if(max < a[j]) {
                        max = a[j];
                        maxIndex = j;
                    }
                }
                result[i] = max;
                start = maxIndex + 1;
                end++; // now we can only pick another max from new range [start, end]
            }
            return result;
        }

        // Merge two arrays to make the largest.
        // These two arrays are both sorted from larger to smaller, and none to throw away.
        int[] merge(int[] a1, int[] a2) {
            int[] result = new int[a1.length + a2.length];
            for(int i=0, j=0, k=0; k<result.length; k++) {
                if(i == a1.length)
                    result[k] = a2[j++];
                else if(j == a2.length)
                    result[k] = a1[i++];
                else {
                    result[k] = compare(a1, i, a2, j) > 0 ? a1[i++] : a2[j++];
                }
            }
            return result;
        }

        // Compare two arrays, starting from index i and j respectively.
        // If finally one ends earlier, consider it smaller.
        int compare(int[] a1, int i, int[] a2, int j) {
            for(; i<a1.length && j<a2.length; i++, j++) {
                int diff = a1[i] - a2[j];
                if(diff != 0)
                    return diff;
            }
            return a1.length - i - (a2.length - j);
        }


        public void test() {
            int[] a1 = {6, 7};
            int[] a2 = {6, 0, 4};
            int k = 5;
            int[] result = maxNumber(a1, a2, k);
            for(int i : result)
                System.out.print(i + " ");
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
