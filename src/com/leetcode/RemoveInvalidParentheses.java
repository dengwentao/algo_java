package com.leetcode;
import java.util.*;

/**
 * Created by wentaod on 11/11/15.
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 Note: The input string may contain letters other than the parentheses ( and ).
 */
public class RemoveInvalidParentheses {
    static public class Solution {

        public List<String> removeInvalidParentheses(String s) {
            if(s == null || s.isEmpty()) {
                List<String> res = new LinkedList<>();
                res.add("");
                return res;
            }

            return BFS(s);
        }

        // try removing a single '(' or ')' each time, and see if it's a valid string.
        List<String> BFS(String s) {
            List<String> result = new LinkedList<>();
            HashSet<String> visited = new HashSet<>();
            Queue<String> q = new LinkedList<>();
            q.offer(s);
            q.offer(null); // as a boundary in one level.
            while(!q.isEmpty()) {
                String elem = q.poll();

                if(elem == null) { // one level is done.
                    if(!result.isEmpty())
                        break; // already has results, so it must be minimum.
                    q.offer(null); // record boundary
                    continue;
                }

                if(visited.contains(elem))
                    continue;
                visited.add(elem);

                if(isValid(elem)) {
                    result.add(elem);
                }
                else if(result.isEmpty()) {
                    // it's invalid and there's no result in this level yet, we need to go to next level.
                    for(int i=0; i<elem.length(); i++) {
                        StringBuilder sb = new StringBuilder(elem);
                        sb.deleteCharAt(i);
                        q.offer(sb.toString());
                    }
                }
            }
            return result;
        }

        // check if s is valid. s is not null or empty.
        boolean isValid(String s) {
            int v = 0;
            for(char c : s.toCharArray()) {
                if(c == '(')
                    v++;
                else if(c == ')') {
                    v--;
                    if(v < 0)
                        return false;
                }
            }
            return v==0;
        }

        public void test() {
            String input = "()))()))(("; //"(((()(()"; //"(a)())()"; //
            for(String result : removeInvalidParentheses(input)) {
                System.out.println(result);
            }
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
