package com.leetcode;
import java.util.*;

/**
 * Remove Duplicate Letters
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 Example:
 Given "bcabc"
 Return "abc"
 Given "cbacdcbc"
 Return "acdb"
 * Created by wentaod on 6/15/16.
 */
public class RemoveDuplicateLetters {
    static public class Solution {
        public String removeDuplicateLetters(String s) {
            if (s == null || s.isEmpty())
                return "";

            int[] hash = new int[26]; // keeps the last position of each char
            for (int i=0; i<s.length(); i++) {
                if (hash[s.charAt(i) - 'a'] < i)
                    hash[s.charAt(i) - 'a'] = i;
            }

            boolean[] set = new boolean[26]; // indicate if a char is on stack already.
            Stack<Character> stack = new Stack<>();

            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);

                if (set[c - 'a']) // already on stack, we can discard. Stack is always in ascending order.
                    continue;

                while (!stack.isEmpty()) {
                    Character top = stack.peek();
                    if (c < top) { // if this is a reversed order, we want to see if the stack top can be discarded.
                        if (hash[top - 'a'] > i) { // if current stack top char has occurrence after c, we can discard it
                            stack.pop();
                            set[top - 'a'] = false;
                            continue;
                        } else // else we have to push c on top because there's no more.
                            break;
                    } else // else the new char maintains ascending order
                        break;
                }
                stack.push(c);
                set[c - 'a'] = true;
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            return sb.reverse().toString();
        }

        public void test() {
            System.out.println(removeDuplicateLetters("bcacdcbc"));
            System.out.println(removeDuplicateLetters("bbcaac"));
            System.out.println(removeDuplicateLetters("cbacdcbc"));
            System.out.println(removeDuplicateLetters("cadcabdbb"));
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
