package com.leetcode;
import java.net.Inet4Address;
import java.util.*;

/**
 * Minimum Window Substring
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".
 * Created by wentaod on 12/2/15.
 */

// Given 2 strings A and B, find the minimum substring in A that contains all the chars in B with best time complexity.
// e.g., A: "xCxxAxxAxBxCx", B: "ACB", answer: "AxBxC".

// algorithm
// test cases; two BB

import java.util.*;

public class MinimumWindowSubstring {

    String minSubStringInclude(String input, String pattern) {
        if (input == null || pattern == null || pattern.isEmpty() || input.isEmpty() || input.length() < pattern.length()) {
            return null;
        }

        Map<Character, Integer> freq = statiscsPattern(pattern);

        int p = 0;
        int q = 0;
        int fulfilled = 0;

        // initially expand to cover all
        while (q < input.length()) {
            Character c = input.charAt(q ++);
            Integer f = freq.get(c);
            if (f == null) {
                continue;
            }
            freq.put(c, ++ f);
            if (f == 0) {
                if (++ fulfilled == freq.size()) {
                    break;
                }
            }
        }

        // at this point, all pattern chars have frequency 0

        int minP = 0;
        int minQ = q; // one over window
        int minSize = q;
        while (p < input.length()) {
            // always try shrinking first
            Character c = input.charAt(p);
            Integer f = freq.get(c);
            if (f == null || f > 0) { // we can shrink in either case
                if (f != null) {
                    freq.put(c, f - 1);
                }
                p ++;
                int size = q - p;
                if (size < minSize) {
                    minP = p;
                    minQ = q;
                    minSize = size;
                }

                continue; // we continue shrinking if we've shrinked this time
            }

            // if q moved to the end, we can still shrink, but if we can no longer shrink, it's done.
            if (q == input.length()) {
                break;
            }

            // If we cannot shink this time, we try expanding
            while (q < input.length()) {
                c = input.charAt(q++);
                f = freq.get(c);
                if (f != null) {
                    freq.put(c, f + 1);
                    break; // we take a needed new char, so we try shrinking next time
                }
            }
        }

        return input.substring(minP, minQ);

    }

    Map<Character, Integer> statiscsPattern(String pattern) {
        Map<Character, Integer> freq = new HashMap<>();
        for (Character c : pattern.toCharArray()) {
            Integer f = freq.get(c);
            if (f == null) {
                f = 0;
            }
            freq.put(c, f - 1);
        }
        return freq;
    }

    static public void main(String args[]) {
        MinimumWindowSubstring sol = new MinimumWindowSubstring();
        System.out.println(sol.minSubStringInclude("xCxxAxxAxBxCx", "ACB"));
        System.out.println(sol.minSubStringInclude("", ""));
        System.out.println(sol.minSubStringInclude("A", "A"));
        System.out.println(sol.minSubStringInclude("A", "AA"));
        System.out.println(sol.minSubStringInclude("A", "AB"));
        System.out.println(sol.minSubStringInclude("AB", "AB"));
        System.out.println(sol.minSubStringInclude("AB", "B"));
        System.out.println(sol.minSubStringInclude("AAxBB", "AB"));
        System.out.println(sol.minSubStringInclude("AAxBA", "AB"));
        System.out.println(sol.minSubStringInclude("AAxBAAB", "ABA"));
    }
}
