package com.leetcode;
import java.util.*;

/**
 * Alien Dictionary
 There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, wherewords are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 For example,
 Given the following words in dictionary,
 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]
 The correct order is: "wertf".

 Note:
 You may assume all letters are in lowercase.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.
 * Created by wd186013 on 11/2/18.
 */
public class AlienDictionary {
    public static String alienOrder(String[] words) {
        if (words == null || words.length < 2)
            return null;

        try {
            Map<Character, Set<Character>> lessMap = genLessMap(words);
            return topoSort(lessMap);
        } catch (Exception e) {
            // System.out.println(e);
            // e.printStackTrace();
            return null;
        }
    }

    // return a map from a char to all chars that are less than it
    private static Map<Character, Set<Character>> genLessMap(String[] words) throws Exception {
        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 1; i < words.length; i ++) {
            String a = words[i - 1];
            String b = words[i];
            boolean allEqual = true;
            for (int j = 0; j < a.length() && j < b.length(); j ++) {
                if (a.charAt(j) == b.charAt(j))
                    continue;
                allEqual = false;
                Set<Character> sb = map.get(a.charAt(j));
                if (sb == null) {
                    sb = new HashSet<Character>();
                    map.put(a.charAt(j), sb);
                }
                sb.add(b.charAt(j));
                break;
            }
            if (allEqual && a.length() > b.length())
                throw new Exception("Invalid.");
        }
        return map;
    }

    private static String topoSort(Map<Character, Set<Character>> map) throws Exception {
        Set<Character> visited = new HashSet<>();
        Set<Character> path = new HashSet<>();
        Stack<Character> s = new Stack<>();
        for (Character c : map.keySet()) {
            dfs(c, map, visited, s, path);
        }

        if (s.empty())
            throw new Exception("Invalid.");

        StringBuilder sb = new StringBuilder();
        while (!s.empty()) {
            sb.append(s.pop());
        }

        return sb.toString();
    }

    private static void dfs(Character c, Map<Character, Set<Character>> map, Set<Character> visited,
                            Stack<Character> s, Set<Character> path) throws Exception {
        if (path.contains(c)) { // there's a cyclic
            throw new Exception("Invalid.");
        }
        if (visited.contains(c))
            return;
        visited.add(c);
        path.add(c);
        Set<Character> sb = map.get(c);
        if (sb != null) {
            for (Character x : sb) {
                dfs(x, map, visited, s, path);
            }
        }
        s.push(c);
        path.remove(c);
    }

    public static void main(String args[]) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};

        System.out.println(alienOrder(words));
    }
}
