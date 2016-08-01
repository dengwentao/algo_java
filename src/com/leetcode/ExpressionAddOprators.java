package com.leetcode;
import java.util.*;

/**
 * Expression Add Operators
 * Created by wentaod on 10/7/15.
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 Examples:
 "123", 6 -> ["1+2+3", "1*2*3"]
 "232", 8 -> ["2*3+2", "2+3*2"]
 "105", 5 -> ["1*0+5","10-5"]
 "00", 0 -> ["0+0", "0-0", "0*0"]
 "3456237490", 9191 -> []
 */
public class ExpressionAddOprators {
    static public class Solution2 {

        List<String> result;
        char[] path;
        char[] digits;
        int target;

        public List<String> addOperators(String num, int target) {
            this.result = new LinkedList<>();

            if (num.length() == 0)
                return result;

            this.target = target;
            this.path = new char[num.length() * 2 - 1]; // to hold one possible solution, for temporary use
            this.digits = num.toCharArray();

            long cur = 0;
            for (int i = 0; i < digits.length; i++) {
                cur = cur * 10 + digits[i] - '0'; // calculated result so far for this path
                path[i] = digits[i]; // digits[i] is taken, so we put it into the path
                dfs(i + 1, 0, cur, i + 1);
                if (cur == 0)
                    break;
            }

            return result;
        }

        // DFS, try each position with none/+/-/*
        // len: length of path already taken
        // left:
        // cur:
        // pos:
        void dfs(int len, long left, long cur, int pos) {
            if (pos == digits.length) {
                if (left + cur == target)
                    result.add(new String(path, 0, len));
                return;
            }

            long n = 0;
            int j = len + 1;
            for (int i = pos; i < digits.length; i++) {
                n = n * 10 + digits[i] - '0';

                path[j++] = digits[i]; // no operator

                path[len] = '+'; // operator +
                dfs(j, left + cur, n, i + 1);

                path[len] = '-'; // operator -
                dfs(j, left + cur, -n, i + 1);

                path[len] = '*'; // operator *
                dfs(j, left, cur * n, i + 1);

                if (digits[pos] == '0')
                    break;
            }
        }


        public void test() {
            for(String exp : addOperators("105", 5))
                System.out.println(exp);
        }
    }

    static public class Solution {

        List<String> results;
        int target;
        long digits[];

        public List<String> addOperators(String num, int target) {
            if (num == null || num.isEmpty())
                return new ArrayList<>();

            this.results = new ArrayList<>(num.length() * 2 - 1);
            this.target = target;
            this.digits = new long[num.length()];
            for (int i=0; i<num.length(); i++) {
                digits[i] = num.charAt(i) - '0';
            }

            tryOperators(0, 1, 0, 1, new StringBuilder());

            return results;
        }

        // DFS: try none/*/+/- after each positions
        void tryOperators(int index, long prod, long sum, long curNum, StringBuilder path) {
            if (index == digits.length) {
                //
                return;
            }

            // try all options after each position [index, end)
            for (int i=index; i<digits.length; i++) { // for loop itself means trying none on each position
                // try *
                path.append(digits[i]);
                prod *= curNum;
                curNum = digits[i];
                //tryOperators(i + 1, prod, sum, curNum);

                // try +
                sum += prod * curNum;
                curNum = digits[i];
                prod = 1;

                // try -
                sum += prod * curNum;
                curNum = digits[i];
                prod = -1;
            }
        }

        public void test() {
            for(String exp : addOperators("105", 5))
                System.out.println(exp);
        }
    }

        static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
