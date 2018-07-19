package com.leetcode;

/**
 * Shifting Letters
 * Created by wd186013 on 7/18/18.
 * We have a string S of lowercase letters, and an integer array shifts.
 Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
 Return the final string after all such shifts to S are applied.
 Example: Input: S = "abc", shifts = [3,5,9], Output: "rpl"
 Note:
 1 <= S.length = shifts.length <= 20000
 0 <= shifts[i] <= 10 ^ 9

 */
public class ShiftingLetters {

     public static class Solution {
        public String shiftingLetters(String S, int[] shifts) {
            char[] arr = S.toCharArray();
            long sum = 0;
            for (int i = shifts.length - 1; i >= 0; i --) {
                sum += shifts[i];
                arr[i] = (char)('a' + (arr[i] - 'a' + sum) % 26);
            }

            return new String(arr);
        };

        public void test() {
            System.out.print(shiftingLetters("mkgfzkkuxownxvfvxasy", new int[]{505870226,437526072,266740649,224336793,532917782,311122363,567754492,595798950,81520022,684110326,137742843,275267355,856903962,148291585,919054234,467541837,622939912,116899933,983296461,536563513}));
        }
    };

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.test();
    }
}
