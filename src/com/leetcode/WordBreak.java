package com.leetcode;
import java.util.*;

/**
 * Word Break II My Submissions QuestionEditorial Solution
 Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 Return all such possible sentences.
 * Created by wentaod on 3/28/16.
 */
public class WordBreak {

    static public class Solution {
        String input;
        Set<String> dict;
        HashMap<Integer, List<String>> dp;

        public List<String> wordBreak(String s, Set<String> wordDict) {
            if(s==null || s.isEmpty() || wordDict==null || wordDict.isEmpty())
                return new LinkedList<>();

            this.input = s;
            this.dict = wordDict;
            this.dp = new HashMap<Integer, List<String>>();

            return S(0, s.length());
        }

        int key(int start, int end) {
            return start * input.length() + end;
        }

        // return all possible sentences of substring [start, end)
        List<String> S(int start, int end) {
            if(start == end)
                return new LinkedList<>();

            int key = key(start, end);
            if(dp.containsKey(key))
                return dp.get(key);

            List<String> words = new LinkedList<>();
            for(int i=start+1; i<=end; i++) {
                String prefix = input.substring(start, i);
                if(!dict.contains(prefix))
                    continue;
                if(i==end) {
                    words.add(prefix);
                    continue;
                }
                for(String s : S(i, end))
                    words.add(prefix + ' ' + s);
            }

            dp.put(key, words);
            return words;
        }

        public void test() {
            String s = "catsanddog";
            Set<String> wordDict = new HashSet<>();
            wordDict.add("cat");
            wordDict.add("cats");
            wordDict.add("and");
            wordDict.add("sand");
            wordDict.add("dog");
            for(String sentence : wordBreak(s, wordDict))
                System.out.println(sentence);
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
