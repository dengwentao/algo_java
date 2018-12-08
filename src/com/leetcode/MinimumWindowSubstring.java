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

    String minSubStringInclude2(String input, String pattern) {
        if (input == null || pattern == null || pattern.isEmpty() || input.isEmpty() || input.length() < pattern.length()) {
            return "";
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

        // it's possible that there's no such window
        if (fulfilled != freq.size())
            return "";

        // at this point, all pattern chars have frequency 0 or positive

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

    // This is another solution.
    public String minSubStringInclude(String s, String t) {
        if (s == null || t == null)
            return "";

        int countNeeded = t.length(); // needed count of all chars. countNeeded == 0 is the invariant.
        int[] dict = new int[128];
        for (char c : t.toCharArray())
            dict[c] ++; // frequencies of each char in the pattern

        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;
        int right = 0, left = 0;
        while (right < s.length()) {
            // Expand the window
            if (dict[s.charAt(right)] > 0) // if this char is needed to satisfy the invariant
                countNeeded --;
            // Count it into frequencies, even though it's not needed to satisfy the invariant.
            dict[s.charAt(right)] --;
            right ++; // right always point to one after valid window.

            // Shrink the window only if all needed chars are in. Note that there may be extra chars.
            while (countNeeded == 0) {
                if (right - left < minLen) { // each time shrinking, update min window.
                    startIndex = left;
                    minLen = right - left;
                }

                if (dict[s.charAt(left)] == 0) // after throwing this char, invariant is broken.
                    countNeeded ++;
                dict[s.charAt(left)] ++; // update frequencies after throwing this char
                left ++; // throw this char
            }
        }

        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLen);
    }

    static public void main(String args[]) {
        MinimumWindowSubstring sol = new MinimumWindowSubstring();
        System.out.println(sol.minSubStringInclude("ADOBECODEBANC", "ABC"));
/*
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
*/
    }
}
