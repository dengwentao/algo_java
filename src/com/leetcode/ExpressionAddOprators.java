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
    // Wrong solution because it cannot handle - well.
    static public class SolutionII {

        class Pair {
            String exp;
            int target;

            public Pair(String exp, int target) {
                this.exp = exp;
                this.target = target;
            }

            public boolean equals(Pair p) {
                return exp.equals(p.exp) && target == p.target;
            }

            public int hashCode() {
                return (exp+':'+target).hashCode();
            }
        }

        // Memoization cache. If an entry has empty value, it means no solution in this path.
        HashMap<Pair, List<String>> mem = new HashMap<>();

        public List<String> addOperators(String num, int target) {
            if(num == null || num.isEmpty())
                return new ArrayList<>();
            return tryOperators(num, target, true);
        }

        // canAdd: if then we have * in front, then an expression cannot have + or - inside.
        private List<String> tryOperators(String num, int target, boolean canAdd) {
            List<String> results = new ArrayList<>();

            Pair pair = new Pair(num, target);
            if (mem.get(pair) != null)
                return mem.get(pair);

            // add ops after index i. if i points to last number, add no ops.
            for (int i = 0; i < num.length(); i++) {
                int part1 = Integer.valueOf(num.substring(0, i + 1));
                String part2 = num.substring(i+1); // could be empty

                if(part2.isEmpty()) {
                    if(target == part1)
                        results.add(num);
                    break; // no possible solution in this path
                }

                if(canAdd) {
                    List<String> resPlus = tryOperators(part2, target - part1, true); // part1 + part2 = target
                    for (String r : resPlus)
                        results.add("" + part1 + "+" + r);
                    List<String> resMinus = tryOperators(part2, part1 - target, true); // part1 - part2 = target
                    for (String r : resMinus)
                        results.add("" + part1 + "-" + r);
                }

                // for multiplication
                List<String> resMul = null;
                if(part1 == 0 && target == 0) {
                    // part2 can be any * combined expressions, e.g., 23 or 2*3
                    resMul = tryAnyMulOperators(part2);
                }
                else if(part1 != 0 && target % part1 == 0) {
                    resMul = tryOperators(part2, target / part1, false); // part1 * part2 = target
                }

                if(resMul != null) {
                    for (String r : resMul)
                        results.add("" + part1 + "*" + r);
                }
            }

            mem.put(pair, results);
            return results;
        }

        // add * in any places.
        private List<String> tryAnyMulOperators(String exp) {
            List<String> results = new LinkedList<>();
            for(int i=0; i<exp.length(); i++) {
                if(i == exp.length()-1) {
                    results.add(exp);
                    return results;
                }
                List<String> resMul = tryAnyMulOperators(exp.substring(i + 1));
                for(String r : resMul)
                    results.add(exp.substring(0, i+1) + "*" + r);
            }
            return results;
        }

        public void test() {
            for(String exp : addOperators("1234", 6))
                System.out.println(exp);
        }
    }

    static public class Solution {
        String num;
        int target;

        public List<String> addOperators(String num, int target) {
            if(num == null || num.isEmpty())
                return new ArrayList<String>();
            this.num = num;
            this.target = target;
            return tryOperators();
        }

        private List<String> tryOperators() {
            return new ArrayList<String>();
        }

        public void test() {
            for(String exp : addOperators("1234", 6))
                System.out.println(exp);
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
