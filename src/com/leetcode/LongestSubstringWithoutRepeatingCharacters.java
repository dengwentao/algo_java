package com.leetcode;
import java.util.*;

/**
 * Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 * Created by wentaod on 12/2/15.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    static public class Solution {
        public int lengthOfLongestSubstring(String s) {
            if(s==null || s.isEmpty())
                return 0;

            HashMap<Character, Integer> freq = new HashMap<>();
            int start=0, end=0;
            int size = 0;
            while(end<s.length()) {
                // expand window
                char c = s.charAt(end);
                Integer cnt = freq.get(c);
                if(cnt == null) {
                    freq.put(c, 1);
                    end++;
                    if(size < end-start)
                        size = end-start;
                }
                else {
                        freq.remove(s.charAt(start));
                        start++;
                }
            }
            return size;
        }

        public void test() {
            System.out.println(lengthOfLongestSubstring("abcabcbb"));
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
