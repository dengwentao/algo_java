package com.leetcode;
import java.util.*;

/**
 * Course Schedule
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * Created by wentaod on 1/7/16.
 */
public class CourseSchedule {
    static public class Solution {
        static class Node {
            int index;
            HashSet<Node> next;

            Node(int index) {
                this.index = index;
                next = new HashSet<>();
            }
        }

        HashMap<Integer, Node> nodes;
        HashSet<Node> visited;

        void initialize(int numCourses, int[][] prerequisites) {
            visited = new HashSet<>();
            nodes = new HashMap<>();
            for(int i=0; i<numCourses; i++)
                nodes.put(i, new Node(i));

            for(int[] pair : prerequisites){
                nodes.get(pair[1]).next.add(nodes.get(pair[0]));
            }
        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if(numCourses<=1 || prerequisites.length == 0)
                return true;

            initialize(numCourses, prerequisites);
            for(Node node : nodes.values()) {
                if(!visited.contains(node))
                    if(hasCyclic(node, new HashSet<Node>()))
                        return false;
            }
            return true;
        }

        // do a DFS starting from node, and return true if a cyclic is detected.
        // while visiting, mark visited, also put the node into traces.
        boolean hasCyclic(Node node, HashSet<Node> traces) {
            if(node == null)
                return false;
            if(traces.contains(node))
                return true;
            if(visited.contains(node))
                return false;
            traces.add(node);
            visited.add(node);
            for(Node child : node.next) {
                if(hasCyclic(child, traces))
                    return true;
            }
            traces.remove(node); // traces is used more like a stack.
            return false;
        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            if(numCourses==0)
                return new int[]{};

            int[] result = new int[numCourses];
            initialize(numCourses, prerequisites);

            Stack<Integer> s = new Stack<>();
            for(Node node : nodes.values()) {
                if(!visited.contains(node))
                    if(DFS(node, s, new HashSet<Node>()))
                        return new int[] {}; // no path possible
            }

            int i = 0;
            while(!s.isEmpty())
                result[i++] = s.pop();
            return result;
        }

        boolean DFS(Node node, Stack<Integer> s, HashSet<Node> traces) {
            if(node == null)
                return false;
            if(traces.contains(node))
                return true;
            if(visited.contains(node))
                return false;
            visited.add(node);
            traces.add(node);
            for(Node n : node.next) {
                if(DFS(n, s, traces))
                    return true;
            }
            s.push(node.index);
            traces.remove(node);
            return false;
        }

        public void test() {
            int count = 3;
            int[][] dependencies = {{0,1}, {2, 0}, {1,2}};
            System.out.println(canFinish(count, dependencies));
            for(int i : findOrder(count, dependencies))
                System.out.print(i + ", ");
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
