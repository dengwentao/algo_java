package com.leetcode;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Different Ways to Add Parentheses
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways
 * to group numbers and operators. The valid operators are +, - and *.
 Example 1
 Input: "2-1-1".
 ((2-1)-1) = 0
 (2-(1-1)) = 2
 Output: [0, 2]
 Example 2
 Input: "2*3-4*5"
 (2*(3-(4*5))) = -34
 ((2*3)-(4*5)) = -14
 ((2*(3-4))*5) = -10
 (2*((3-4)*5)) = -10
 (((2*3)-4)*5) = 10
 Output: [-34, -14, -10, -10, 10]
 * Created by wentaod on 8/18/15.
 */
public class WaysParentheses {
    static public class Solution {

        enum Type {NUM, OP};
        static class Token {
            Type type;
            String term;

            public Token(Type type, String term) {
                this.type = type;
                this.term = term;
            }

            public Token(int i) {
                this.type = Type.NUM;
                this.term = String.format("%d", i);
            }

            public Type getType() {
                return type;
            }

            public int getNum() throws Exception {
                if(type == Type.OP)
                    throw new Exception("getNum met OP.");
                return Integer.valueOf(term);
            }

            public char getOp() throws Exception {
                if(type == Type.NUM)
                    throw new Exception("getOp met NUM.");
                return term.charAt(0);
            }

            public void display() {
                System.out.println("Type="+type+", Term="+term);
            }
        }

        List<Token> tokens;
        HashMap<Integer, List<Integer>> dp = new HashMap<>();
        int slot(int start, int end) {return (start<<16) + end;}

        List<Token> parseInput(String input) {
            List<Token> tokens = new ArrayList<>();
            String[] parts = input.split("[+*-]");
            int index = 0;
            for(String part : parts) {
                tokens.add(new Token(Type.NUM, part));
                index += part.length();
                if(index == input.length())
                    break;
                tokens.add(new Token(Type.OP, input.substring(index, index+1)));
                index++;
            }
            return tokens;
        }

        // calculate num1<op>num2
        int reduceThreeTerms(int num1, char op, int num2) throws Exception {
            switch (op) {
                case '+':
                    return (num1 + num2);
                case '-':
                    return (num1 - num2);
                case '*':
                    return (num1 * num2);
            }
            throw new Exception("Wrong operator.");
        }

        // input tokens, and returns all possible results in the range [start, end]
        // make sure start and end indexes both point to a num.
        List<Integer> reduceTokens(int start, int end) {
            List<Integer> results = dp.get(slot(start, end));
            if(results != null && results.size()>0)
                return results;

            results = new ArrayList<>();

            try {
                if (end == start) {
                    results.add(tokens.get(start).getNum());
                    return results;
                }
                if (end-start == 2) {
                    results.add(reduceThreeTerms(tokens.get(start).getNum(), tokens.get(start+1).getOp(), tokens.get(start+2).getNum()));
                    return results;
                }

                for (int i = start+1; i < end; i += 2) {
                    char op = tokens.get(i).getOp();
                    List<Integer> nums1 = reduceTokens(start, i - 1);
                    List<Integer> nums2 = reduceTokens(i+1, end);
                    for (int n1 : nums1) {
                        for (int n2 : nums2)
                            results.add(reduceThreeTerms(n1, op, n2));
                    }
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            dp.put(slot(start, end), results);
            return results;
        }

        public List<Integer> diffWaysToCompute(String input) {
            List<Integer> result = new ArrayList<>();

            if(input==null || input.length()==0)
                return result;

            tokens = parseInput(input);
            List<Integer> l = new ArrayList<Integer>();

            result = reduceTokens(0, tokens.size()-1);
            Collections.sort(result);

            return result;
        }

        public void test() {
            String expr = "2*3-4*5";
            for (Integer way : diffWaysToCompute(expr)) {
                System.out.println(way);
            }
        }
    }

    static public void main(String[] args) {
        Solution sol = new Solution();
        sol.test();
    }
}
