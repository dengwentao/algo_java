package com.leetcode;
import java.util.Arrays;

/**
 * Created by wentaod on 7/30/15.
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
 For example, given the following matrix:
 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0
 Return 4.
 */
public class MaxSquare {
    static public class Solution {
        public int maximalSquare(char[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;
            int side = 0; // current width of the square.
            int[] baseline = new int[matrix[0].length];
            Arrays.fill(baseline, 0);
            for(int row=0; row<matrix.length; row++) {
                // update baseline. Baseline is the sum of all rows up to this row.
                // when an element is 0, reset to baseline to 0 for this col.
                for(int col=0; col<matrix[0].length; col++) {
                    if(matrix[row][col] == '0')
                        baseline[col] = 0;
                    else
                        baseline[col] ++;
                }
                // scan baseline to see if there's continuous cols larger than previous width.
                int count = 0;
                for(int col=0; col<matrix[0].length; col++) {
                    if(baseline[col] > side)
                        count++;
                    else
                        count = 0;
                    if(count > side) {
                        side ++;
                        break;
                    }
                }
            }
            return side * side;
        }

        public void test() {
            char[][] matrix = { {'1', '0', '1', '1', '1'},
                                {'1', '0', '1', '1', '1'},
                                {'1', '1', '1', '1', '0'},
                                {'1', '0', '0', '1', '1'}
            };
            System.out.println(maximalSquare(matrix));
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
