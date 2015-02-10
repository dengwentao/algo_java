package com.company;
import java.util.*;

/**
 * Created by wedeng on 2/9/15.
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle
 [
    [2],
   [3,4],
  [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:
 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==0)
            return 0;
        if(triangle.size()==1)
            return triangle.get(0).get(0);

        int min = 0;
        for(int i=triangle.size()-2; i>=0; i--) {
            List<Integer> curLevel = triangle.get(i);
            List<Integer> lowerLevel = triangle.get(i+1);
            for(int j=0; j<curLevel.size(); j++) {
                min = curLevel.get(j) + Math.min(lowerLevel.get(j), lowerLevel.get(j+1));
                curLevel.set(j, min);
            }
        }
        return min;
    }

    void test() {
        List<Integer> l1 = new LinkedList<Integer>(Arrays.asList(2));
        List<Integer> l2 = new LinkedList<Integer>(Arrays.asList(3,4));
        List<Integer> l3 = new LinkedList<Integer>(Arrays.asList(6,5,7));
        List<Integer> l4 = new LinkedList<Integer>(Arrays.asList(4,1,8,3));
        List<List<Integer>> triangle = new LinkedList<List<Integer>>(Arrays.asList(l1, l2, l3, l4));
        System.out.println(minimumTotal(triangle));
    }

    public static void main(String[] args) {
        Triangle sol = new Triangle();
        sol.test();
    }
}

