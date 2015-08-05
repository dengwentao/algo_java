package com.leetcode;

import java.util.HashMap;

/**
 * Number of Digit One
 * Created by wentaod on 8/5/15.
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

 For example: Given n = 13, Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberOfOnes {
    static class Solution {
        HashMap<Integer, Integer> cache = new HashMap<>();
        public int countDigitOne(int n) {
            if(n < 1)
                return 0;

            //cache.clear(); //no need to clear.
            return count(n);
        }

        private int count(int n) {
            if(n < 1)
                return 0;
            if(n <= 9)
                return 1;
            if(cache.get(n) != null)
                return cache.get(n);

            int x = n;
            int num = 0; // count of digits of n
            while(x != 0) {
                x = x / 10;
                num++;
            }
            int zeros = (int)Math.pow(10, num-1); // for 29 or 19, this is 10.
            int top = n / zeros; // for 28, this is 2
            int remainder = n % zeros; // for 24, this is 4

            int countOfOnes = 0;
            if(top == 1)
                countOfOnes = remainder + 1 + count(remainder) + count(zeros - 1);
            else
                countOfOnes = count(remainder) + count(zeros - 1) * (top - 2) + count(2 * zeros - 1);

            cache.put(n, countOfOnes);
            return countOfOnes;
        }

        public void test() {
            validate(-1, 0);
            validate(0, 0);
            validate(1, 1);
            validate(13, 6);
            validate(33, 14);

            System.out.println(countDigitOne(3184191));
        }

        private boolean validate(int n, int c) {
            int count = countDigitOne(n);
            if(count != c) {
                System.out.println("Input "+n+" expect "+c+" but got "+count);
                return false;
            }
            else
                return true;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.test();
    }
}
