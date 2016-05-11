package com.leetcode;

/**
 * Created by wentaod on 5/11/16.
 */
public class LongestIncreasingPathInMatrix {

    static public class Solution {

        int[][] dp;
        int[][] matrix;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;

            this.matrix = matrix;
            this.dp = new int[matrix.length][matrix[0].length];
            int max = 0;
            for (int i=0; i<matrix.length; i++)
                for (int j=0; j<matrix[0].length; j++) {
                    int steps = DFS(i, j);
                    if (max < steps)
                        max = steps;
                }

            return max;
        }

        // Do a DFS starting from row i col j, and return max steps increasing.
        int DFS(int i, int j) {
            if (dp[i][j] != 0)
                return dp[i][j];

            int steps = 0;
            int trial = 0;
            if (i < dp.length - 1 && matrix[i][j] < matrix[i+1][j]) {
                trial = DFS(i + 1, j);
                if (trial > steps)
                    steps = trial;
            }
            if (i > 0 && matrix[i][j] < matrix[i-1][j]) {
                trial = DFS(i - 1, j);
                if (trial > steps)
                    steps = trial;
            }
            if (j < dp[0].length - 1 && matrix[i][j] < matrix[i][j+1]) {
                trial = DFS(i, j + 1);
                if (trial > steps)
                    steps = trial;
            }
            if (j > 0 && matrix[i][j] < matrix[i][j-1]) {
                trial = DFS(i, j - 1);
                if (trial > steps)
                    steps = trial;
            }

            dp[i][j] = steps + 1;
            return dp[i][j];
        }

        public void test() {
            int[][] matrix = {
                    {9, 9, 4},
                    {6, 6, 8},
                    {2, 1, 1}
            };
            System.out.println(longestIncreasingPath(matrix));
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
