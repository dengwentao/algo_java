package com.company;
import java.util.*;

/**
 * Created by wedeng on 2/8/15.
 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the dictionary
 For example,

 Given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]
 Return
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]
 Note:
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 */

class SolutionWordLadder {
    class Entry {
        ArrayList<String> path;

        public Entry() {
            path = new ArrayList<String>();
        }

        public Entry(String word) {
            this();
            path.add(word);

        }

        public Entry(Entry e) {
            path = new ArrayList<String>(e.path);
        }

        public void addWord(String word) {
            path.add(word);
        }

        public String getLast() {
            return path.get(path.size()-1);
        }

        public int size() {
            return path.size();
        }

        public ArrayList<String> getPath() {
            return path;
        }
    }

    String start;
    String end;
    Set<String> dict;
    HashSet<String> visited = new HashSet<String>();
    Queue<Entry> queue = new LinkedList<Entry>();

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        if(start.equals(end))
            return new ArrayList<List<String>>();
        if(start.length()!=end.length() || dict.isEmpty())
            return null;

        this.start = start;
        this.end = end;
        this.dict = dict;

        Entry entry = new Entry(start);
        queue.offer(entry);
        visited.add(start);

        return BFS();
    }

    //BFS until end is found.
    List<List<String>> BFS() {
        List<List<String>> res = new ArrayList<List<String>>();
        int level = 0;
        boolean found = false;
        List<String> visitedCurLevel = new ArrayList<String>(); //to record visited words in current level.
        while(!queue.isEmpty()) {
            Entry e = queue.poll();
            if(e.size()>level) {//we are in a new level.
                level = e.size();
                if(found)
                    break; //if already found in upper level, we don't need to visit this level.
                else { //we shouldn't revisit a word appeared in upper level, but on the same level it's OK.
                    visited.addAll(visitedCurLevel);
                    visitedCurLevel.clear();
                }
            }
            String s = e.getLast();
            //System.out.println(s);
            if(s.equals(end)) {
                found = true;
                res.add(e.getPath());
                level = e.size();
                //we don't need to update visited in this case.
            }
            if(found) //if we found in this level, we don't go to next level any more.
                continue;
            for (String next : variations(s)) {
                if (dict.contains(next) && !visited.contains(next)) {
                    Entry entry = new Entry(e);
                    entry.addWord(next);
                    queue.offer(entry);
                    visitedCurLevel.add(next);
                }
            }
        }
        return res;
    }

    //return a list of all strings which has 1 step from s.
    List<String> variations(String s) {
        List<String> res = new ArrayList<String>();
        for(int i=0; i<s.length(); i++) {
            StringBuilder sb = new StringBuilder(s);
            for(char c ='a'; c<='z'; c++)
                if(c!=s.charAt(i)) {
                    sb.setCharAt(i, c);
                    res.add(sb.toString());
                }
        }
        return res;
    }

    public void test() {
        String start = "hit";
        String end = "cod";
        Set<String> dict = new HashSet<String>(Arrays.asList("hot","dot","dog","lot","log", "cog"));
        dict.add(end);
        List<List<String>> res = findLadders(start, end, dict);
        for(List<String> l : res) {
            for(String s : l)
                System.out.print(s + ' ');
            System.out.println();
        }
    }
}

public class WordLadder {
    public static void main(String[] args) {
        SolutionWordLadder sol = new SolutionWordLadder();
        sol.test();
    }
}
