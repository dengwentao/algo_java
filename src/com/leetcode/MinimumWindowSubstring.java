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
public class MinimumWindowSubstring {
    static public class Solution {

        public String minWindow(String s, String t) {
            if(s==null || t==null || s.isEmpty() || t.isEmpty())
                return "";

            // Decide pattern in template
            HashMap<Character, Integer> template = new HashMap<>();
            for(int i=0; i<t.length(); i++) {
                char c = t.charAt(i);
                Integer count = template.get(c);
                if(count==null)
                    count = 0;
                template.put(c, count+1);
            }

            // Initialize all needed chars counts to 0.
            HashMap<Character, Integer> pattern = new HashMap<>();
            for(char c : template.keySet())
                    pattern.put(c, 0);

            // Window [start, end] is the satisfied window.
            // When sliding window, make sure count is greater or equal to the template.
            int start=0, end=0;
            int minSize = Integer.MAX_VALUE;
            String minStr = "";
            boolean satisfied = false;
            for(;end < s.length(); end++) {
                // expand window to the right
                char c = s.charAt(end);
                Integer cnt = pattern.get(c);
                if(cnt != null) {
                    pattern.put(c, cnt + 1);
                    // we no longer need to check if window satisfied once it's satisfied based on our logic.
                    if(!satisfied)
                        satisfied = isSatisfied(template, pattern);
                    // Now that a new char is counted in, we can try to shrink window.
                    while(start<=end) {
                        char x = s.charAt(start);
                        Integer xnt = pattern.get(x);
                        if(xnt!=null) {
                            if(xnt <= template.get(x))
                                break; // we cannot shrink further.
                            pattern.put(x, xnt-1);
                        }
                        start++;
                    }
                    if(satisfied && minSize > end - start + 1) {
                        minSize = end - start + 1;
                        minStr = s.substring(start, end+1);
                    }
                }
            }

            return minStr;
        }

        boolean isSatisfied(HashMap<Character, Integer> template, HashMap<Character, Integer> pattern) {
            for(Map.Entry<Character, Integer> e : template.entrySet()) {
                if(pattern.get(e.getKey())==null || pattern.get(e.getKey()) < e.getValue()) // no such pattern
                    return false;
            }
            return true;
        }

        public void test() {
            String s = "ADOBECODEBANC";
            String t = "ABC";
            System.out.println(minWindow(s, t));
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
