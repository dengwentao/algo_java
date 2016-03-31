package com.leetcode;
import java.util.*;

/**
 * Created by wentaod on 3/25/16.
 */
public class LargestRectangleInHistogram {
    static public class Solution {
        static class Pair {
            int start; // start index of the rectangle
            int height; // height of the rectangle
            public Pair(int start, int height) {
                this.start = start;
                this.height = height;
            }
        }

        public int largestRectangleArea(int[] heights) {
            if(heights == null || heights.length == 0)
                return 0;

            Stack<Pair> s = new Stack<>();
            s.push(new Pair(0, heights[0]));
            int max = 0;
            for(int i=1; i<=heights.length; i++) {
                int height = i==heights.length ? 0 : heights[i];
                int start = i;
                while(!s.empty() && height < s.peek().height) {
                    Pair pair = s.pop();
                    int area = (i - pair.start) * pair.height;
                    if(area > max)
                        max = area;
                    start = pair.start;
                }
                s.push(new Pair(start, height));
            }

            return max;
        }

        public void test() {
            int[] heights = {3,6,5,7,4,8,1,0};//{2,1,5,6,2,3};
            System.out.println(largestRectangleArea(heights));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
