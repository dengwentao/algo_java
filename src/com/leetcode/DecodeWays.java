package com.leetcode;

import java.util.HashMap;

/**
 * Decode Ways
 * Created by wentaod on 8/3/15.
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.
 For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 The number of ways decoding "12" is 2.
 */
public class DecodeWays {
    static class Solution {
        String s;
        HashMap<Integer, Integer> cache = new HashMap<>();

        public int numDecodings(String s) {
            if(s==null || s.length()==0)
                return 0;
            this.s = s;
            cache.clear();
            if(s.length() == 1)
                return singleValid(0) ? 1 : 0;

            return decodeWays(0);
        }

        // return 0-9 for the char at index, -1 indicates error.
        private int digit(int index) {
            int i = s.charAt(index)>='0' && s.charAt(index)<='9' ? s.charAt(index)-'0' : -1;
            return i;
        }

        private boolean singleValid(int index) {
            return digit(index) >= 1;
        }

        private boolean doubleValid(int index) {
            return digit(index)==1 && digit(index+1)!=-1 || digit(index)==2 && digit(index+1)>=0 && digit(index+1)<=6;
        }

        private int decodeWays(int index) {
            if(index == s.length())
                return 1;
            if(index == s.length()-1)
                return singleValid(index) ? 1 : 0;
            if(cache.get(index) != null)
                return cache.get(index);

            int ways = (singleValid(index) ? decodeWays(index+1) : 0) + (doubleValid(index) ? decodeWays(index+2) : 0);
            cache.put(index, ways);
            return ways;
        }

        private boolean validate(String input, int num) {
            int count = numDecodings(input);
            if(count != num) {
                System.out.println("Input: "+input+", expect "+num+" but got "+count);
                return false;
            }
            else
                return true;
        }

        public void test() {
            validate("2121", 5);//2 1 2 1, 21 2 1, 21 21, 2 1 21, 2 12 1
            validate("20110", 1);//20 1 10
            validate("", 0);
            validate("1", 1);
            validate("0", 0);
            validate("501", 0);
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
