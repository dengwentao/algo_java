package com.leetcode;
import java.util.*;

/**
 * Scramble String
 * https://leetcode.com/problems/scramble-string/
 * Created by wentaod on 12/6/15.
 */
public class ScrambleString {
    static public class Solution {
        String s1, s2;
        int len;
        int[][][] dp;
        public boolean isScramble(String s1, String s2) {
            if(s1.isEmpty() && s2.isEmpty())
                return true;
            if(s1.length() != s2.length())
                return false;
            this.s1 = s1;
            this.s2 = s2;
            this.len = s1.length();
            dp = new int[len+1][len+1][len+1];

            return checkScramble(0, 0, len) == 1;
        }

        // check if s1[start1, start1+len-1] and s2[start2, start2+len-1] is scramble. Return 1 if true, else 2.
        int checkScramble(int start1, int start2, int len) {
            if(dp[start1][start2][len] != 0)
                return dp[start1][start2][len];

            if(len==1) {
                dp[start1][start2][len] = s1.charAt(start1) == s2.charAt(start2) ? 1 : 2;
                return dp[start1][start2][len];
            }

            //Direction 1: reversed
            int freq[] = new int[128];
            for(int i=0; i<len-1; i++) { // when coming in, both strings already has the same frequencies
                freq[s1.charAt(start1+i)]++;
                freq[s2.charAt(start2+len-1-i)]--;
                if(isFreqEmpty(freq)) {
                    if(checkScramble(start1, start2+len-1-i, i+1)==1 && checkScramble(start1+i+1, start2, len-i-1)==1) {
                        dp[start1][start2][len] = 1;
                        return dp[start1][start2][len];
                    }
                }
            }

            //Direction 2: the same
            freq = new int[128];
            for(int i=0; i<len-1; i++) { // when coming in, both strings already has the same frequencies
                freq[s1.charAt(start1+i)]++;
                freq[s2.charAt(start2+i)]--;
                if(isFreqEmpty(freq)) {
                    if(checkScramble(start1, start2, i+1)==1 && checkScramble(start1+i+1, start2+i+1, len-i-1)==1) {
                        dp[start1][start2][len] = 1;
                        return dp[start1][start2][len];
                    }
                }
            }

            dp[start1][start2][len] = 2;
            return dp[start1][start2][len];
        }

        boolean isFreqEmpty(int freq[]) {
            for(int i=0; i<freq.length; i++)
                if(freq[i] != 0)
                    return false;
            return true;
        }



        public void test() {
            System.out.println(isScramble("rgtae", "great"));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
