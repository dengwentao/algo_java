package com.leetcode;
import java.util.*;

/**
 Range Sum Query 2D - Immutable.
 Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Created by wentaod on 11/16/15.
 */
public class RangeSumQuery2D {
    static public class NumMatrix {

        int[][] matrix;
        int height;
        int width;

        // Hold sum of rectange defined by (row, col) and (width-1, height-1).
        int[][] dp;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            if(matrix!=null) {
                height = matrix.length;
                if(height!=0)
                    width = matrix[0].length;
            }
            if(width==0 || height==0)
                return;

            dp = new int[height+1][];
            for(int i=0; i<=height; i++)
                dp[i] = new int[width+1];

            for(int row=height-1; row>=0; row--) {
                for(int col=width-1; col>=0; col--) {
                    dp[row][col] = matrix[row][col] + dp[row+1][col] + dp[row][col+1] - dp[row+1][col+1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if(width==0 || height==0)
                return 0;

            return dp[row1][col1] - dp[row1][col2+1] - dp[row2+1][col1] + dp[row2+1][col2+1];
        }
    }

    static public void main(String args[]) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(0, 0, 1, 2));
    }
}
