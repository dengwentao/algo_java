package com.leetcode;
import java.util.*;

/**
 * The Skyline Problem
 * Created by wentaod on 9/23/15.
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 [Notes]
 - The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 - The input list is already sorted in ascending order by the left x position Li.
 - The output list must be sorted by the x position.
 - There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */

public class SkyLine {

    static public class Solution {

        public List<int[]> getSkyline(int[][] buildings) {
            if(buildings == null || buildings.length == 0)
                return new ArrayList<int[]>();
            return getSkyline(buildings, 0, buildings.length-1);
        }

        // input buildings array, return skyline from [start, end]
        // recursively divide and conquer.
        private List<int[]> getSkyline(int[][] buildings, int start, int end) {
            if(end < start)
                return new ArrayList<int[]>();
            else if(start == end) {
                int[][] a = {{buildings[start][0], buildings[start][2]}, {buildings[start][1], 0}};
                return Arrays.asList(a);
            }
            else {
                int mid = (end - start) / 2 + start;
                List<int[]> skyLine1 = getSkyline(buildings, start, mid);
                List<int[]> skyLine2 = getSkyline(buildings, mid + 1, end);
                return combineSkyLine(skyLine1, skyLine2);
            }
        }

        // combine two skylines with merge sort algorithm.
        private List<int[]> combineSkyLine(List<int[]> skyLine1, List<int[]> skyLine2) {
            List<int[]> result = new ArrayList<int[]>();
            int i = 0;
            int j = 0;
            while(i < skyLine1.size() && j < skyLine2.size()) {
                int[] p = skyLine1.get(i);
                int[] q = skyLine2.get(j);
                if(p[0] < q[0]) {
                    int prevY = j==0 ? 0 : skyLine2.get(j-1)[1];
                    if(result.isEmpty() || Math.max(prevY, p[1]) != result.get(result.size()-1)[1]) {
                        if (p[1] >= prevY)
                            result.add(new int[]{p[0], p[1]});
                        else
                            result.add(new int[]{p[0], prevY});
                    }
                    i++;
                }
                else if(p[0] > q[0]){
                    int prevY = i==0 ? 0 : skyLine1.get(i-1)[1];
                    if(result.isEmpty() || Math.max(prevY, q[1]) != result.get(result.size()-1)[1]) {
                        if (q[1] >= prevY)
                            result.add(new int[]{q[0], q[1]});
                        else if (prevY != result.get(result.size() - 1)[1])
                            result.add(new int[]{q[0], prevY});
                    }
                    j++;
                }
                else {
                    if(p[1] <= q[1])
                        i++;
                    else
                        j++;
                }
            }

            //remaining part
            if(i < skyLine1.size()) {
                if(skyLine1.get(i)[1] == result.get(result.size()-1)[1])
                    i++;
                if(i < skyLine1.size())
                 result.addAll(skyLine1.subList(i, skyLine1.size()));
            }
            else if(j < skyLine2.size()) {
                if(skyLine2.get(j)[1] == result.get(result.size()-1)[1])
                    j++;
                if(j < skyLine2.size())
                 result.addAll(skyLine2.subList(j, skyLine2.size()));
            }

            return result;
        }

        public void test() {
            //int[][] buildings = {{0, 2, 3}, {2, 5, 3}};
            int[][] buildings = { {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
            List<int[]> keys = getSkyline(buildings);
            for(int[] key : keys) {
                System.out.format("(%d, %d) ", key[0], key[1]);
            }
        }

    }


    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
