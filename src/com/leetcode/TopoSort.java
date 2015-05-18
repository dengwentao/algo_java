package com.leetcode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by WentaoD on 5/13/15.
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 */


class Topo {

  private HashSet<Integer> visited;
  private HashMap<Integer, ArrayList<Integer>> map;
  private Stack<Integer> stack = new Stack<>();
  private int num;

  //[[3,5]..[]] means 3 depends on 5
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] order = new int[numCourses];

    if(numCourses==0)
      return order;
    else if(numCourses==1) {
      order[0] = 0;
      return order;
    }

    num = numCourses;
    visited = new HashSet<>(numCourses);
    map = new HashMap<>(numCourses);
    for(int i=0; i<numCourses; i++)
      map.put(i, new ArrayList<Integer>());
    for(int[] req : prerequisites) {
      if(req[0]==req[1])
        continue;
      map.get(req[1]).add(req[0]);
    }

    try {
      for (int i = 0; i < numCourses; i++)
        DFS(i, 0);
    }
    catch(Exception e) {
      System.out.println(e.getMessage());
      return new int[0];
    }

    int i = 0;
    while(!stack.empty())
      order[i++] = stack.pop();
    return order;
  }

  // DFS the DAG from the given course and put order into the stack
  // depth is used to detect cyclic
  private void DFS(int course, int depth) throws Exception {
    if(visited.contains(course))
      return;
    if(depth==num)
      throw new Exception("Cyclic detected!");
    for(int c : map.get(course)) {
      DFS(c, depth+1);
    }
    stack.push(course);
    visited.add(course);
  }

  public void test() {
    int numCourses = 6;
    int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}, {4,5}};
    int[] order = findOrder(numCourses, prerequisites);
    for(int o : order)
      System.out.println(o);
  }
}

public class TopoSort {
  public static void main(String args[]) {
    Topo topo = new Topo();
    //topo.test();
    int[] a = new int[0];
    System.out.println(a.length);
  }
}


