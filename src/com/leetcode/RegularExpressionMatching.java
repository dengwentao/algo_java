package com.leetcode;

/**
 * Created by wentaod on 11/23/15.
 * Regular Expression Matching
 * Implement regular expression matching with support for '.' and '*'.
 */
public class RegularExpressionMatching {
    static public class Solution {
        char[] source;
        char[] pattern;

        public boolean isMatch(String s, String p) {
            if(s==null || p==null)
                return false;
            source = s.toCharArray();
            pattern = p.toCharArray();
            return isMatch(0, 0);
        }

        private boolean isMatch(int sourceIndex, int patternIndex) {
            if(patternIndex == pattern.length)
                return sourceIndex == source.length;
            else if(patternIndex == pattern.length-1)
                return (sourceIndex==source.length-1) && (source[sourceIndex]==pattern[patternIndex] || pattern[patternIndex]=='.');

            // patternIndex <= pattern.length-2
            if(pattern[patternIndex+1] == '*') {
                if(isMatch(sourceIndex, patternIndex+2))
                    return true;
                for(int i=sourceIndex; i<source.length && (pattern[patternIndex]=='.' || pattern[patternIndex]==source[i]); i++) {
                    if(isMatch(i+1, patternIndex+2))
                        return true;
                }
                return false;
            }
            else {
                return sourceIndex<source.length && (source[sourceIndex]==pattern[patternIndex] || pattern[patternIndex]=='.') && isMatch(sourceIndex+1, patternIndex+1);
            }
        }

        private void check(boolean result, boolean expect) {
            if(result != expect)
                System.out.println("Wrong!");
            else
                System.out.println("Correct!");
        }

        public void test() {
            check(isMatch("abcd", "d*"), false);
            check(isMatch("", ""), true);
            check(isMatch("", ".*"), true);
            check(isMatch("", "c*"), true);
            check(isMatch("", "*"), false);
            check(isMatch("aab", "c*a*b"), true);
            check(isMatch("aabbb", "c*a*.*"), true);
            check(isMatch("ab", ".*c"), false);
            check(isMatch("a", ".*.c*"), true);
            check(isMatch("a", "ab*a"), false);
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
