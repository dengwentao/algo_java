package com.leetcode;

/**
 * Interleaving String
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 For example,
 Given:
 s1 = "aabcc",
 s2 = "dbbca",
 When s3 = "aadbbcbcac", return true.
 When s3 = "aadbbbaccc", return false.
 * Created by wentaod on 12/5/15.
 */
public class InterleavingString {
    static public class Solution {
        String s1, s2, s3;
        int[][] dp;
        public boolean isInterleave(String s1, String s2, String s3) {
            if(s1==null || s2==null || s3==null || s1.length()+s2.length()!=s3.length())
                return false;
            if(s1.isEmpty() && s2.isEmpty() && s3.isEmpty())
                return true;
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            dp = new int[s1.length()+1][s2.length()+1];
            return isIntl(s1.length(), s2.length()) == 1;
        }

        int isIntl(int len1, int len2) {
            if(dp[len1][len2] != 0)
                return dp[len1][len2];
            if(len1==0 && len2==0)
                dp[len1][len2] = 1;
            else
                dp[len1][len2] = ((len1>0 && s1.charAt(len1-1)==s3.charAt(len1+len2-1) && isIntl(len1-1, len2)==1
                                || len2>0 && s2.charAt(len2-1)==s3.charAt(len1+len2-1) && isIntl(len1, len2-1)==1) ? 1 : 2);
            //System.out.println("len1="+len1+" len2="+len2+" result="+dp[len1][len2]);
            return dp[len1][len2];
        }

        void test() {
            String s1 = "aabcc";
            String s2 = "dbbca";
            String s3 = "aadbbbaccc";
            boolean interleave = isInterleave(s1, s2, s3);
            System.out.print(interleave);
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
