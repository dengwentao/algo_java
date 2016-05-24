package com.leetcode;
import java.util.*;

/**
 * Palindrome Pairs
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 Example 1:
 Given words = ["bat", "tab", "cat"]
 Return [[0, 1], [1, 0]]
 The palindromes are ["battab", "tabbat"]
 * Created by wentaod on 5/23/16.
 */
public class PalindromePairs {
    static public class Solution {

        // prefix tree
        static class Node {
            char c; // character of this node
            int index; // point to index in input words array if this is the end node of a word
            Node[] children; // children nodes

            public Node() {
                c = '\0';
                index = -1;
                children = new Node[26];
            }

            public Node(char c) {
                this();
                this.c = c;
            }

            // insert word into sub-trie which has this node as root
            // index is the index in the original input
            // i is the index of char to start with in word
            public void insert(String word, int i, int index) {
                if (i == word.length()) {
                    this.index = index;
                    return;
                }
                char c = word.charAt(i);
                if (children[c - 'a'] == null)
                    children[c - 'a'] = new Node(c);
                children[c - 'a'].insert(word, i+1, index);
            }

            // find out all words in the trie which create palindromes when inserted in front of the input word.
            // return a list of indexes in the original input.
            // i is index of char in word
            public List<Integer> find(String word, int i) {
                List<Integer> result = new ArrayList<>();
                if (index != -1) { // current node is the end of a word
                    if (isPalindrome(word.substring(i)))
                        result.add(index);
                }
                if (i == word.length()) { // word ends here. If the remaining subtree has a palindrome, then the combination is a panlindrome.
                    result.addAll(findPalindromes());
                    return result;
                }
                char c = word.charAt(i);
                if (children[c - 'a'] != null) {
                    result.addAll(children[c - 'a'].find(word, i+1));
                }
                return result;
            }

            // check all branches in the sub-trie and return index if the branch is a palindrome.
            public List<Integer> findPalindromes() {
                return traverse("");
            }

            // traverse current subtree. When meeting an end, check if it's a palindrome. Return index if yes.
            private List<Integer> traverse(String s) {
                List<Integer> result = new ArrayList<>();
                for (int i=0; i<26; i++) {
                    Node n = children[i];
                    if (n != null) {
                        String s2 = s + n.c;
                        if (n.index != -1) {
                            if (isPalindrome(s2))
                                result.add(n.index);
                        }
                        result.addAll(n.traverse(s2));
                    }
                }
                return result;
            }

            // utility to check if word is a palindrome
            boolean isPalindrome(String word) {
                int i = 0;
                int j = word.length() - 1;
                while (i < j) {
                    if (word.charAt(i) != word.charAt(j))
                        return false;
                    i++;
                    j--;
                }
                return true;
            }
        }

        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> result = new ArrayList<>();
            if (words == null || words.length < 2) {
                return result;
            }

            Node root = new Node();
            for (int i=0; i<words.length; i++) {
                root.insert(words[i], 0, i);
            }

            //for (int i : root.findPalindromes())
            //    System.out.println(i);

            for (int i=0; i<words.length; i++) {
                StringBuilder sb = new StringBuilder(words[i]);
                List<Integer> l = root.find(sb.reverse().toString(), 0);
                for (int j : l) {
                    if (i == j)
                        continue;
                    List<Integer> pair = new ArrayList<>();
                    pair.add(j);
                    pair.add(i);
                    result.add(pair);
                }
            }

            return result;
        }

        public void test() {
            String[] words = {"abcd", "dcba", "lls", "s", "sssll", ""};
            for (List<Integer> pair : palindromePairs(words)) {
                System.out.println(" (" + pair.get(0) + ", " + pair.get(1) + ") ");
            }
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
