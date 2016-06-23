package com.leetcode;

import java.util.*;

/**
 * Max Sum of Rectangle No Larger Than K
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
 * Created by wentaod on 6/22/16.
 */
public class MaxSumOfRectangleNoLargerThanK {
    static public class Solution {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;

            int[][] matrixFlat = matrix;
            if (matrix.length > matrix[0].length) { // make the matrix lower
                matrixFlat = new int[matrix[0].length][matrix.length];
                for (int row = 0; row < matrixFlat.length; row++) {
                    for (int col = 0; col <  matrixFlat[0].length; col++) {
                        matrixFlat[row][col] = matrix[col][row];
                    }
                }
            }

            return maxSum(matrixFlat, k);
        }

        private int maxSum(int[][] matrix, int k) {
            int max = Integer.MIN_VALUE;
            for (int top = 0; top < matrix.length; top++) {
                int[] colSum = new int[matrix[0].length];
                for (int btm = top; btm < matrix.length; btm++) {
                    TreeSet set = new TreeSet();
                    set.add(0); // otherwise when col==0 there's no element
                    int prefix = 0;
                    for (int col = 0; col < matrix[0].length; col++) {
                        colSum[col] += matrix[btm][col];
                        prefix += colSum[col]; // update to get prefix sum in current column
                        Integer prevPrefix = (Integer) set.ceiling(prefix - k); // find smallest value to make a diff of <=k in previous prefix sums
                        if (prevPrefix != null) { // if found
                            int rangeSum = prefix - prevPrefix;
                            if (rangeSum == k)
                                return k;
                            if (rangeSum > max)
                                max = rangeSum;
                        }

                        set.add(prefix);
                    }
                }
            }
            return max;
        }

        public void test() {
            int[][] matrix = {
                    {1,  0, 1},
                    {0, -2, 3}
            };
            int k = 5;

            System.out.println(maxSumSubmatrix(matrix, k));
        }
    }

    static public void main(String[] args) {
        Solution solution = new Solution();
        solution.test();
    }
}
