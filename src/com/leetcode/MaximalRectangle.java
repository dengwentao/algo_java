package com.leetcode;

/**
 * Maximal Rectangle
 * Created by wentaod on 3/28/16.
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */
public class MaximalRectangle {
    static public class Solution {
        public int maximalRectangle(char[][] matrix) {
            if(matrix==null || matrix.length==0 || matrix[0].length==0)
                return 0;

            int max = 0;
            int[] array = new int[matrix[0].length];
            for(int i=0; i<matrix.length; i++) {
                for(int j=0; j<matrix[0].length; j++) {
                    if(matrix[i][j]=='0')
                        array[j] = 0;
                    else
                        array[j]++;
                }

                int rect = maxRectHisto(array);
                if(rect > max)
                    max = rect;
            }

            return max;
        }

        // max rectangle in histogram
        int maxRectHisto(int[] array) {
            LargestRectangleInHistogram.Solution sol = new LargestRectangleInHistogram.Solution();
            return sol.largestRectangleArea(array);
        }

        public void test() {
            char[][] matrix = {
                    {'0', '0', '1', '1', '0', '1', '0'},
                    {'1', '1', '1', '1', '0', '1', '1'},
                    {'1', '0', '0', '1', '0', '1', '1'},
                    {'1', '0', '1', '1', '1', '0', '1'},
                    {'1', '0', '1', '1', '1', '0', '0'},
                    {'1', '1', '1', '0', '0', '1', '0'}
            };
            System.out.println(maximalRectangle(matrix));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
