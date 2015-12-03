package com.leetcode;

/**
 * Longest Palindromic Substring
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * Created by wentaod on 12/2/15.
 */
public class LongestPalindromicSubstring {
    static public class Solution {

        public String longestPalindrome(String s) {
            if(s==null || s.length()<2)
                return s;

            int size = 0;
            int start=0, end=0;
            for(int mid=0; mid<s.length(); mid++) {
                int len = findLongest(s, mid, mid); // center is mid
                if(len > size) {
                    size = len;
                    start = mid - (len-1)/2;
                    end = mid + (len-1)/2;
                }
                len = findLongest(s, mid, mid+1); // center is in the middle of mid and mid+1
                if(len > size) {
                    size = len;
                    start = mid - len/2 + 1;
                    end = mid + len/2;
                }
            }
            return s.substring(start, end+1);
        }

        // find longest palindrome by expanding left and right.
        int findLongest(String s, int left, int right) {
            for(; left>=0 && right<s.length(); left--, right++) {
                if(s.charAt(left) != s.charAt(right))
                    break;
            }
            return right - left - 1;
        }

        public void test() {
            System.out.println(longestPalindrome("abccbaabccba"));
        }
    }


    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
