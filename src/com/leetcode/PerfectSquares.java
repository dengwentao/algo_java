package com.leetcode;

import java.util.*;

/**
 * Perfect Squares
 * Created by wentaod on 10/20/15.
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class PerfectSquares {
    static public class Solution {

        int[] dp;

        public int numSquares(int n) {
            dp = new int[n+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            dp[1] = 1;
            return count(n);
        }

        int count(int n) {

            if(dp[n] != Integer.MAX_VALUE)
                return dp[n];

            int root = (int)Math.sqrt(n);
            for(int i=root; i>0; i--) {
                int remainder = n - i*i;
                if(remainder == 0) {
                    dp[n] = 1;
                    break;
                }
                else {
                    dp[n] = Math.min(dp[n], 1 + count(remainder));
                }
            }

            return dp[n];
        }

        public int numSquares2(int nn) {
            int[] dp = new int[nn+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            dp[1] = 1;

            for(int n=2; n<=nn; n++) {
                int root = (int) Math.sqrt(n);
                for (int i = root; i > 0; i--) {
                    int remainder = n - i * i;
                    if (remainder == 0) {
                        dp[n] = 1;
                        break;
                    } else {
                        dp[n] = Math.min(dp[n], 1 + dp[remainder]);
                    }
                }
            }

            return dp[nn];
        }

        void test() {
            System.out.println(numSquares(12));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
